package goal.jalal.goaljalal.auth.presentation;

import goal.jalal.goaljalal.auth.exception.AuthenticationException;
import goal.jalal.goaljalal.auth.jwt.JwtTokenExtractor;
import goal.jalal.goaljalal.auth.jwt.JwtTokenProvider;
import goal.jalal.goaljalal.member.domain.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class MemberInterceptor implements HandlerInterceptor {

    private final JwtTokenExtractor jwtTokenExtractor;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        final String accessToken = jwtTokenExtractor.extractAccessToken(request);
        Long kakaoId = jwtTokenProvider.extractKakaoIdFromAccessToken(accessToken);
        validateMemberExist(kakaoId);
        return true;
    }

    private void validateMemberExist(final Long kakaoId) {
        if (notExistByKakaoId(kakaoId)) {
            String logMessage = "인증 실패(존재하지 않는 멤버) - 회원 이메일 : " + kakaoId;
            throw new AuthenticationException.FailAuthenticationException(logMessage);
        }
    }

    private boolean notExistByKakaoId(Long kakaoId) {
        return !memberRepository.existsByKakaoId(kakaoId);
    }
}
