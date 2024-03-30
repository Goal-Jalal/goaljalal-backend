package goal.jalal.goaljalal.member.exception.member;

import goal.jalal.goaljalal.common.exception.CustomBadRequestException;

public class BirthDateLengthException extends CustomBadRequestException {

    public BirthDateLengthException(final int allowedLength, final String inputBirthDate) {
        super(String.format(
            "생년월일의 길이가 최대 길이를 초과했습니다. - request info { allowedLength : %d, input_value_length : %d }",
            allowedLength,
            inputBirthDate.length()));
    }
}
