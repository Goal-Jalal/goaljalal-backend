package goal.jalal.goaljalal.club.exception.club;

import goal.jalal.goaljalal.common.exception.CustomBadRequestException;

public class NameLengthException extends CustomBadRequestException {

    public NameLengthException(final int allowedLength, final int inputValue) {

        super(String.format(
            "클럽 이름의 길이가 최대 이름 크기를 초과했습니다. - request info { allowed_length : %d, input_value_length : %d }",
            allowedLength,
            inputValue));
    }
}
