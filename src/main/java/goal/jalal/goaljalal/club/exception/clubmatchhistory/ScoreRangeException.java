package goal.jalal.goaljalal.club.exception.clubmatchhistory;

import goal.jalal.goaljalal.common.exception.CustomBadRequestException;

public class ScoreRangeException extends CustomBadRequestException {

    public ScoreRangeException(final int score) {
        super(String.format("스코어는 0 - 200 사이의 값이어야만 합니다. - request { %d }", score));
    }
}
