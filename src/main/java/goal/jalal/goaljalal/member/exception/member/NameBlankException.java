package goal.jalal.goaljalal.member.exception.member;

import goal.jalal.goaljalal.common.exception.CustomBadRequestException;

public class NameBlankException extends CustomBadRequestException {

    public NameBlankException() {
        super("멤버 이름은 공백을 제외한 1자 이상이어야 합니다.");
    }
}
