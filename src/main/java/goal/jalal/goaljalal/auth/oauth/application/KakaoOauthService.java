package goal.jalal.goaljalal.auth.oauth.application;

import goal.jalal.goaljalal.auth.jwt.JwtTokenProvider;
import goal.jalal.goaljalal.auth.oauth.application.dto.OauthMember;
import goal.jalal.goaljalal.auth.oauth.application.dto.Properties;
import goal.jalal.goaljalal.auth.oauth.application.dto.TokenResponse;
import goal.jalal.goaljalal.member.domain.Member;
import goal.jalal.goaljalal.member.domain.MemberRepository;
import goal.jalal.goaljalal.member.domain.vo.Name;
import goal.jalal.goaljalal.token.domain.Token;
import goal.jalal.goaljalal.token.domain.TokenRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class KakaoOauthService {

    private static final int NAME_BEGIN_INDEX = 0;

    private final KakaoTokenService kakaoTokenService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;

    public TokenResponse createToken(final String kakaoCode) {
        final String kakaoAccessToken = kakaoTokenService.fetchAccessToken(kakaoCode);
        OauthMember oauthMember = createOauthMember(kakaoAccessToken);

        final Member member = createMemberIfNotExist(oauthMember);

        final String accessToken = jwtTokenProvider.generateAccessToken(oauthMember.kakaoId());
        final String refreshToken = jwtTokenProvider.generateAccessToken(oauthMember.kakaoId());

        saveOrUpdateRefreshToken(member, refreshToken);

        log.info("토큰 생성 - 사용자 카카오 아이디 : {}", oauthMember.kakaoId());
        return new TokenResponse(accessToken, refreshToken);
    }

    private OauthMember createOauthMember(final String oauthAccessToken) {
        OauthMember oauthMember = kakaoTokenService.getOauthMember(oauthAccessToken);

        final long kakaoId = oauthMember.kakaoId();
        final String name = oauthMember.properties().nickname();
        final String picture = oauthMember.properties().thumbnailImage();

        if (name.length() > Name.MAX_LENGTH) {
            final String subStringName = name.substring(NAME_BEGIN_INDEX, Name.MAX_LENGTH);
            return new OauthMember(kakaoId, new Properties(subStringName, picture));
        }
        return new OauthMember(kakaoId, new Properties(name, picture));
    }

    private Member createMemberIfNotExist(final OauthMember oauthMember) {
        Optional<Member> optionalMember = memberRepository.findByKakaoId(
            oauthMember.kakaoId());
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.updateLoginTimestamp();
            return member;
        }

        final Member member = new Member(oauthMember.kakaoId(), oauthMember.properties().nickname(),
            null,
            oauthMember.properties().thumbnailImage());

        return memberRepository.save(member);
    }

    private void saveOrUpdateRefreshToken(Member member, String refreshToken) {
        tokenRepository.findByMember(member)
            .ifPresentOrElse(
                token -> token.changeToken(refreshToken),
                () -> tokenRepository.save(new Token(member, refreshToken))
            );
    }
}
