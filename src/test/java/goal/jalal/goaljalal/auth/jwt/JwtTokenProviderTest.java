package goal.jalal.goaljalal.auth.jwt;

import static goal.jalal.goaljalal.common.fixtures.MemberFixtures.JOHN_KAKAO_ID;
import static goal.jalal.goaljalal.common.fixtures.TokenFixtures.MALFORMED_JWT_TOKEN;
import static goal.jalal.goaljalal.common.fixtures.TokenFixtures.MISSING_CLAIM_ACCESS_TOKEN;
import static goal.jalal.goaljalal.common.fixtures.TokenFixtures.MISSING_CLAIM_REFRESH_TOKEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import goal.jalal.goaljalal.auth.exception.AuthenticationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtTokenProviderTest {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Nested
    @DisplayName("엑세스 토큰 테스트")
    class AccessTokenTest {

        @Test
        @DisplayName("엑세스 토큰을 정상적으로 생성합니다.")
        void success_Generate_AccessToken() {
            //given
            final Long kakaoId = JOHN_KAKAO_ID;

            //when
            final String accessToken = jwtTokenProvider.generateAccessToken(kakaoId);

            //then
            assertThat(accessToken).isNotNull();
        }

        @Test
        @DisplayName("엑세스 토큰에서 KAKAO_ID가 추출되어야 합니다.")
        void success_Extract_KakaoId_From_AccessToken() {
            //given
            final Long kakaoId = JOHN_KAKAO_ID;
            final String accessToken = jwtTokenProvider.generateAccessToken(kakaoId);

            //when
            final Long extractedKakaoId = jwtTokenProvider.extractKakaoIdFromAccessToken(
                accessToken);

            //then
            assertThat(extractedKakaoId).isEqualTo(kakaoId);
        }

        @Test
        @DisplayName("엑세스 토큰 추출 시 JWT 형식이 다른 토큰 이면 InvalidAccessTokenException 예외가 발생해야 합니다.")
        void extract_KakaoId_From_AccessToken_Invalid_ExceptionThrown() {
            //given
            final String malFormedJwtToken = MALFORMED_JWT_TOKEN;

            //when & then
            assertThatThrownBy(
                () -> jwtTokenProvider.extractKakaoIdFromAccessToken(malFormedJwtToken))
                .isInstanceOf(AuthenticationException.InvalidAccessTokenException.class)
                .hasMessageContaining(
                    String.format("인증 실패(잘못된 액세스 토큰) - request info { token : %s }",
                        malFormedJwtToken));
        }

        @Test
        @DisplayName("엑세스 토큰 추출 시 KakaoId 클레임이 없는 토큰이면 AccessTokenClaimNullException 예외가 발생해야 합니다.")
        void extract_KakaoId_From_AccessToken_ClaimNull_ExceptionThrown() {
            // given
            String missingClaimToken = MISSING_CLAIM_ACCESS_TOKEN;

            // when & then
            assertThatThrownBy(
                () -> jwtTokenProvider.extractKakaoIdFromAccessToken(missingClaimToken))
                .isInstanceOf(AuthenticationException.AccessTokenClaimNullException.class)
                .hasMessageContaining(String.format(
                    "인증 실패(JWT 액세스 토큰 Payload KakaoId 누락) - request info { token : %s }",
                    missingClaimToken));
        }
    }

    @Nested
    @DisplayName("리프레시 토큰 테스트")
    class RefreshToken {

        @Test
        @DisplayName("리프레시 토큰을 정상적으로 생성합니다.")
        void success_Generate_AccessToken() {
            //given
            final Long kakaoId = JOHN_KAKAO_ID;

            //when
            final String accessToken = jwtTokenProvider.generateRefreshToken(kakaoId);

            //then
            assertThat(accessToken).isNotNull();
        }

        @Test
        @DisplayName("리프레시 토큰에서 KAKAO_ID가 추출되어야 합니다.")
        void success_Extract_KakaoId_From_RefreshToken() {
            //given
            final Long kakaoId = JOHN_KAKAO_ID;
            final String accessToken = jwtTokenProvider.generateRefreshToken(kakaoId);

            //when
            final Long extractedKakaoId = jwtTokenProvider.extractKakaoIdFromRefreshToken(
                accessToken);

            //then
            assertThat(extractedKakaoId).isEqualTo(kakaoId);
        }

        @Test
        @DisplayName("리프레시 토큰 추출 시 JWT 형식이 다른 토큰 이면 InvalidRefreshTokenException 예외가 발생해야 합니다.")
        void extract_KakaoId_From_RefreshToken_Invalid_ExceptionThrown() {
            //given
            final String malFormedJwtToken = MALFORMED_JWT_TOKEN;

            //when & then
            assertThatThrownBy(
                () -> jwtTokenProvider.extractKakaoIdFromRefreshToken(malFormedJwtToken))
                .isInstanceOf(AuthenticationException.InvalidRefreshTokenException.class)
                .hasMessageContaining(
                    String.format("인증 실패(잘못된 리프레시 토큰) - request info { token : %s }",
                        malFormedJwtToken));
        }

        @Test
        @DisplayName("리프레시 토큰 추출 시 KakaoId 클레임이 없는 토큰이면 RefreshTokenClaimNullException 예외가 발생해야 합니다.")
        void extract_KakaoId_From_RefreshToken_ClaimNull_ExceptionThrown() {
            // given
            String missingClaimToken = MISSING_CLAIM_REFRESH_TOKEN;

            // when & then
            assertThatThrownBy(
                () -> jwtTokenProvider.extractKakaoIdFromRefreshToken(missingClaimToken))
                .isInstanceOf(AuthenticationException.RefreshTokenClaimNullException.class)
                .hasMessageContaining(String.format(
                    "인증 실패(JWT 리프레시 토큰 Payload KakaoId 누락) - request info { token : %s }",
                    missingClaimToken));
        }

    }

}