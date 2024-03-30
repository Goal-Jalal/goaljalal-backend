package goal.jalal.goaljalal.member.exception.member;

import goal.jalal.goaljalal.common.exception.CustomBadRequestException;

public class BirthDateRegexException extends CustomBadRequestException {

    public BirthDateRegexException(final String birthDate) {
        super(String.format("올바른 생년월일 형식이 아닙니다. - request info { date : %s }", birthDate));
    }
}
