package goal.jalal.goaljalal.member.exception.stat;

public class StatException extends RuntimeException {

    public StatException(final String message) {
        super(message);
    }

    public static class StatTotalException extends StatException {

        public StatTotalException(final int statTotal) {
            super(String.format("스탯의 총합은 450을 넘을 수 없습니다. - request info { %s }", statTotal));
        }
    }

    public static class StatLengthException extends StatException {

        public StatLengthException(final int length) {
            super(String.format("스탯의 개수는 6개여야 합니다. - request info { %s }", length));
        }
    }
}
