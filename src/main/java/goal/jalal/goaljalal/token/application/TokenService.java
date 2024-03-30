package goal.jalal.goaljalal.token.application;

import goal.jalal.goaljalal.auth.jwt.JwtTokenProvider;
import goal.jalal.goaljalal.token.application.dto.TokenResponse;
import goal.jalal.goaljalal.token.domain.TokenRepository;
import goal.jalal.goaljalal.token.exception.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;

    public TokenResponse reissueAccessToken(final String refreshToken) {
        if (existRefreshToken(refreshToken)) {
            throw new TokenNotFoundException(refreshToken);
        }
        final Long kakaoId = jwtTokenProvider.extractKakaoIdFromRefreshToken(refreshToken);
        final String accessToken = jwtTokenProvider.generateAccessToken(kakaoId);
        log.info("AccessToken 재발급 - 재발급 받은 사용자 카카오ID : {}", kakaoId);

        return new TokenResponse(accessToken);
    }

    private boolean existRefreshToken(final String refreshToken) {
        return !tokenRepository.existsByRefreshToken(refreshToken);
    }
}
