package goal.jalal.goaljalal.member.exception;

public class MemberException extends RuntimeException {

    public MemberException(final String message) {
        super(message);
    }

    public static class NameLengthException extends MemberException {

        public NameLengthException(final int allowedLength, final String inputName) {
            super(String.format(
                "멤버 이름의 길이가 최대 이름 길이를 초과했습니다. - request info { allowed_length : %d, input_value_length : %d }",
                allowedLength,
                inputName.length()));
        }
    }

    public static class NameBlankException extends MemberException {

        public NameBlankException() {
            super("멤버 이름은 공백을 제외한 1자 이상이어야 합니다.");
        }
    }

    public static class BirthDateLengthException extends MemberException {

        public BirthDateLengthException(final int allowedLength, final String inputBirthDate) {
            super(String.format(
                "생년월일의 길이가 최대 길이를 초과했습니다. - request info { allowedLength : %d, input_value_length : %d }",
                allowedLength,
                inputBirthDate.length()));
        }
    }

    public static class BirthDateRegexException extends MemberException {

        public BirthDateRegexException(final String birthDate) {
            super(String.format("올바른 생년월일 형식이 아닙니다. - request info { date : %s }", birthDate));
        }
    }

    public static class BirthDateBlankException extends MemberException {

        public BirthDateBlankException() {
            super("생년월일은 공백을 제외한 10자리 여야 합니다.");
        }
    }
}
