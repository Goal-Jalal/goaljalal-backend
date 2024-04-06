package goal.jalal.goaljalal.auth.jwt;

import static goal.jalal.goaljalal.common.fixtures.MemberFixtures.JOHN_KAKAO_ID;
import static goal.jalal.goaljalal.common.fixtures.MemberFixtures.JOHN_MEMBER_ID;
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
            final Long MemberId = JOHN_KAKAO_ID;

            //when
            final String accessToken = jwtTokenProvider.generateAccessToken(MemberId);

            //then
            assertThat(accessToken).isNotNull();
        }

        @Test
        @DisplayName("엑세스 토큰에서 Member_ID가 추출되어야 합니다.")
        void success_Extract_MemberId_From_AccessToken() {
            //given
            final Long MemberId = JOHN_MEMBER_ID;
            final String accessToken = jwtTokenProvider.generateAccessToken(MemberId);

            //when
            final Long extractedMemberId = jwtTokenProvider.extractMemberIdFromAccessToken(
                accessToken);

            //then
            assertThat(extractedMemberId).isEqualTo(MemberId);
        }

        @Test
        @DisplayName("엑세스 토큰 추출 시 JWT 형식이 다른 토큰 이면 InvalidAccessTokenException 예외가 발생해야 합니다.")
        void extract_MemberId_From_AccessToken_Invalid_ExceptionThrown() {
            //given
            final String malFormedJwtToken = MALFORMED_JWT_TOKEN;

            //when & then
            assertThatThrownBy(
                () -> jwtTokenProvider.extractMemberIdFromAccessToken(malFormedJwtToken))
                .isInstanceOf(AuthenticationException.InvalidAccessTokenException.class)
                .hasMessageContaining(
                    String.format("인증 실패(잘못된 액세스 토큰) - request info { token : %s }",
                        malFormedJwtToken));
        }

        @Test
        @DisplayName("엑세스 토큰 추출 시 MemberId 클레임이 없는 토큰이면 AccessTokenClaimNullException 예외가 발생해야 합니다.")
        void extract_MemberId_From_AccessToken_ClaimNull_ExceptionThrown() {
            // given
            String missingClaimToken = MISSING_CLAIM_ACCESS_TOKEN;

            // when & then
            assertThatThrownBy(
                () -> jwtTokenProvider.extractMemberIdFromAccessToken(missingClaimToken))
                .isInstanceOf(AuthenticationException.AccessTokenClaimNullException.class)
                .hasMessageContaining(String.format(
                    "인증 실패(JWT 액세스 토큰 Payload MemberId 누락) - request info { token : %s }",
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
            final Long MemberId = JOHN_MEMBER_ID;

            //when
            final String accessToken = jwtTokenProvider.generateRefreshToken(MemberId);

            //then
            assertThat(accessToken).isNotNull();
        }

        @Test
        @DisplayName("리프레시 토큰에서 Member_ID가 추출되어야 합니다.")
        void success_Extract_MemberId_From_RefreshToken() {
            //given
            final Long MemberId = JOHN_MEMBER_ID;
            final String accessToken = jwtTokenProvider.generateRefreshToken(MemberId);

            //when
            final Long extractedMemberId = jwtTokenProvider.extractMemberIdFromRefreshToken(
                accessToken);

            //then
            assertThat(extractedMemberId).isEqualTo(MemberId);
        }

        @Test
        @DisplayName("리프레시 토큰 추출 시 JWT 형식이 다른 토큰 이면 InvalidRefreshTokenException 예외가 발생해야 합니다.")
        void extract_MemberId_From_RefreshToken_Invalid_ExceptionThrown() {
            //given
            final String malFormedJwtToken = MALFORMED_JWT_TOKEN;

            //when & then
            assertThatThrownBy(
                () -> jwtTokenProvider.extractMemberIdFromRefreshToken(malFormedJwtToken))
                .isInstanceOf(AuthenticationException.InvalidRefreshTokenException.class)
                .hasMessageContaining(
                    String.format("인증 실패(잘못된 리프레시 토큰) - request info { token : %s }",
                        malFormedJwtToken));
        }

        @Test
        @DisplayName("리프레시 토큰 추출 시 MemberId 클레임이 없는 토큰이면 RefreshTokenClaimNullException 예외가 발생해야 합니다.")
        void extract_MemberId_From_RefreshToken_ClaimNull_ExceptionThrown() {
            // given
            String missingClaimToken = MISSING_CLAIM_REFRESH_TOKEN;

            // when & then
            assertThatThrownBy(
                () -> jwtTokenProvider.extractMemberIdFromRefreshToken(missingClaimToken))
                .isInstanceOf(AuthenticationException.RefreshTokenClaimNullException.class)
                .hasMessageContaining(String.format(
                    "인증 실패(JWT 리프레시 토큰 Payload MemberId 누락) - request info { token : %s }",
                    missingClaimToken));
        }

    }

}