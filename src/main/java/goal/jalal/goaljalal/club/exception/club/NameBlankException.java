package goal.jalal.goaljalal.club.exception.club;

import goal.jalal.goaljalal.common.exception.CustomBadRequestException;

public class NameBlankException extends CustomBadRequestException {

    public NameBlankException() {

        super("클럽 이름은 공백을 제외한 1자 이상이어야 합니다.");
    }
}
