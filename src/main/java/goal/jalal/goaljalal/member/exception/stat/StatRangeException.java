package goal.jalal.goaljalal.member.exception.stat;

import goal.jalal.goaljalal.common.exception.CustomBadRequestException;

public class StatRangeException extends CustomBadRequestException {

    public StatRangeException() {
        super("올바른 스탯 값이 아닙니다.");
    }
}
