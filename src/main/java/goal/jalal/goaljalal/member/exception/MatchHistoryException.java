package goal.jalal.goaljalal.member.exception;

public class MatchHistoryException extends RuntimeException {

    public MatchHistoryException(final String message) {
        super(message);
    }


    public static class UnknownMatchResult extends MatchHistoryException {

        public UnknownMatchResult(
            String result) {
            super(String.format("알수없는 경기 결과 입니다. -request info { %s } ", result));
        }
    }
}
