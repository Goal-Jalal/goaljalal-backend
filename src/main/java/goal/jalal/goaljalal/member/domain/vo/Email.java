package goal.jalal.goaljalal.member.domain.vo;

import goal.jalal.goaljalal.member.exception.MemberException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Email {

    private static final String EMAIL_REGEX = "^([\\w\\.\\_\\-])*[a-zA-Z0-9]+([\\w\\.\\_\\-])*([a-zA-Z0-9])+([\\w\\.\\_\\-])+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,8}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Column(name = "email", nullable = false, unique = true)
    private String value;

    public Email(final String value) {
        validateNull(value);
        validateEmailRegex(value);
        this.value = value;
    }

    private void validateNull(final String value) {
        if (Objects.isNull(value)) {
            throw new NullPointerException("이메일은 null일 수 없습니다.");
        }
    }

    private void validateEmailRegex(final String value) {
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new MemberException.EmailRegexException(value);
        }
    }
}
