package goal.jalal.goaljalal.member.domain.vo;

import goal.jalal.goaljalal.member.exception.member.BirthDateBlankException;
import goal.jalal.goaljalal.member.exception.member.BirthDateLengthException;
import goal.jalal.goaljalal.member.exception.member.BirthDateRegexException;
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
public class BirthDate {

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * Regex for Matching Date Pattern in Java                 *
     * reference link                                          *
     * https://www.baeldung.com/java-date-regular-expressions  *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    private static final String DATE_REGEX =
        "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
            + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
            + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);
    private static final int MAX_LENGTH = 10;

    @Column(name = "birth_date", length = MAX_LENGTH)
    private String date;

    public BirthDate(final String value) {
        if (Objects.isNull(value)) {
            this.date = null;
            return;
        }
        final String trimmedValue = value.trim();
        validateTrim(trimmedValue);
        validateDateRegex(trimmedValue);
        this.date = trimmedValue;
    }

    private void validateTrim(final String value) {
        if (value.length() > MAX_LENGTH) {
            throw new BirthDateLengthException(MAX_LENGTH, value);
        }

        if (value.isBlank()) {
            throw new BirthDateBlankException();
        }
    }

    private void validateDateRegex(final String date) {
        if (!DATE_PATTERN.matcher(date).matches()) {
            throw new BirthDateRegexException(date);
        }
    }
}
