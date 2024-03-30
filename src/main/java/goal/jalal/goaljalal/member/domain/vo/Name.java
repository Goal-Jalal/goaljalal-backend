package goal.jalal.goaljalal.member.domain.vo;

import goal.jalal.goaljalal.member.exception.MemberException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Name {

    public static final int MAX_LENGTH = 20;

    @Column(name = "name", nullable = false, length = MAX_LENGTH)
    private String value;

    public Name(final String value) {
        validateNull(value);
        final String trimmedValue = value.trim();
        validateTrim(trimmedValue);
        this.value = trimmedValue;
    }

    private void validateNull(final String value) {
        if (Objects.isNull(value)) {
            throw new NullPointerException("멤버 이름은 null일 수 없습니다.");
        }
    }

    private void validateTrim(final String value) {
        if (value.length() > MAX_LENGTH) {
            throw new MemberException.NameLengthException(MAX_LENGTH, value);
        }

        if (value.isBlank()) {
            throw new MemberException.NameBlankException();
        }
    }

    public Name change(final String name) {
        return new Name(name);
    }
}
