package goal.jalal.goaljalal.member.exception;

public class MemberMatchHistoryException extends RuntimeException {

    public MemberMatchHistoryException(final String message) {
        super(message);
    }


    public static class UnknownMemberMatchResult extends MemberMatchHistoryException {

        public UnknownMemberMatchResult(
            String result) {
            super(String.format("알수없는 경기 결과 입니다. -request info { %s } ", result));
        }
    }
}
