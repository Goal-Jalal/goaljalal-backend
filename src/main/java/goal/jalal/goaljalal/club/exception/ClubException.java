package goal.jalal.goaljalal.club.exception;

public class ClubException extends RuntimeException {

    public ClubException(final String message) {
        super(message);
    }

    public static class NameLengthException extends ClubException {

        public NameLengthException(final int allowedLength, final int inputValue) {

            super(String.format(
                "클럽 이름의 길이가 최대 이름 크기를 초과했습니다. - request info { allowed_length : %d, input_value_length : %d }",
                allowedLength,
                inputValue));
        }
    }

    public static class NameBlankException extends ClubException {

        public NameBlankException() {

            super("클럽 이름은 공백을 제외한 1자 이상이어야 합니다.");
        }
    }
}
