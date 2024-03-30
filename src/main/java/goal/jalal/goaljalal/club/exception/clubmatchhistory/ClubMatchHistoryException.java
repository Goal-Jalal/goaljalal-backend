package goal.jalal.goaljalal.club.exception.clubmatchhistory;

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

    public static class ClubMatchFormNotExistException extends ClubMatchHistoryException {

        public ClubMatchFormNotExistException(
            final String matchFormat) {
            super(String.format("존재하지 않는 매치 형태 입니다. - request info { club_match_form : %s }",
                matchFormat));
        }
    }
}
