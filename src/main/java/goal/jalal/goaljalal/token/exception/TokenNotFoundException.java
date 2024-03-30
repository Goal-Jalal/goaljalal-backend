package goal.jalal.goaljalal.token.exception;

import goal.jalal.goaljalal.common.exception.CustomUnauthorizedException;

public class TokenNotFoundException extends CustomUnauthorizedException {

    public TokenNotFoundException(final String email) {
        super(String.format("토큰을 찾을 수 없습니다. - request info { email : %s }", email));
    }
}
