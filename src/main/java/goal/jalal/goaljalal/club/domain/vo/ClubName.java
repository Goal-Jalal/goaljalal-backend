package goal.jalal.goaljalal.club.domain.vo;

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
public class ClubName {

    public static final int MAX_LENGTH = 20;

    @Column(name = "name", nullable = false, length = MAX_LENGTH)
    private String name;

    public ClubName(final String value) {
        validateNull(value);
        final String trimmedValue = value.trim();
        validateTrim(trimmedValue);
        this.name = trimmedValue;
    }

    private void validateNull(final String value) {
        if (Objects.isNull(value)) {
            throw new NullPointerException("클럽 이름은 null일 수 없습니다.");
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

    public ClubName change(final String name) {
        return new ClubName(name);
    }
}
