package goal.jalal.goaljalal.auth.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(final String message) {
        super(message);
    }

    public static class FailAuthenticationException extends AuthenticationException {

        public FailAuthenticationException(final String message) {
            super(message);
        }
    }

    public static class InvalidAccessTokenException extends AuthenticationException {

        public InvalidAccessTokenException(final String token) {
            super(String.format("인증 실패(잘못된 액세스 토큰) - request info { token : %s }", token));
        }
    }

    public static class AccessTokenClaimNullException extends AuthenticationException {

        public AccessTokenClaimNullException(final String token) {
            super(
                String.format("인증 실패(JWT 액세스 토큰 Payload KakaoId 누락) - request info { token : %s }",
                    token));
        }
    }

    public static class InvalidRefreshTokenException extends AuthenticationException {

        public InvalidRefreshTokenException(final String token) {
            super(String.format("인증 실패(잘못된 리프레시 토큰) - request info { token : %s }", token));
        }
    }

    public static class RefreshTokenClaimNullException extends AuthenticationException {

        public RefreshTokenClaimNullException(final String token) {
            super(
                String.format("인증 실패(JWT 리프레시 토큰 Payload KakaoId 누락) - request info { token : %s }",
                    token));
        }
    }
}
