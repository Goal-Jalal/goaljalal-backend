package goal.jalal.goaljalal.club.exception;

public class ClubMatchHistoryException extends RuntimeException {

    public ClubMatchHistoryException(final String message) {
        super(message);
    }

    public static class ClubMatchResultNotExistException extends ClubMatchHistoryException {

        public ClubMatchResultNotExistException(final String matchResultNumber) {
            super(String.format("존재하지 않는 경기 결과입니다. - request info { club_match_result : %s}",
                matchResultNumber));
        }
    }

    public static class ScoreRangeException extends ClubMatchHistoryException {

        public ScoreRangeException(final int score) {
            super(String.format("스코어는 0 - 200 사이의 값이어야만 합니다. - request { %d }", score));
        }
    }

    public static class ClubMatchFormNotExistException extends ClubMatchHistoryException {

        public ClubMatchFormNotExistException(
            final String matchFormat) {
            super(String.format("존재하지 않는 매치 형태 입니다. - request info { club_match_form : %s }",
                matchFormat));
        }
    }
}
