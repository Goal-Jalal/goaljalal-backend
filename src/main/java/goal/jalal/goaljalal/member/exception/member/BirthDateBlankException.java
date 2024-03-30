package goal.jalal.goaljalal.member.exception.member;

import goal.jalal.goaljalal.common.exception.CustomBadRequestException;

public class BirthDateBlankException extends CustomBadRequestException {

    public BirthDateBlankException() {
        super("생년월일은 공백을 제외한 10자리 여야 합니다.");
    }
}
