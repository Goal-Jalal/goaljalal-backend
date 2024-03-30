package goal.jalal.goaljalal.member.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import goal.jalal.goaljalal.member.exception.member.BirthDateBlankException;
import goal.jalal.goaljalal.member.exception.member.BirthDateLengthException;
import goal.jalal.goaljalal.member.exception.member.BirthDateRegexException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BirthDateTest {

    @Test
    @DisplayName("생년월일 null이면 null이 반환되어야 합니다.")
    void birthDateConstructor_Nullvalue_ExceptionThrown() {
        //given
        final String birthDateNull = null;

        //when
        BirthDate newBirthDate = new BirthDate(birthDateNull);

        //then
        assertThat(newBirthDate.getDate()).isNull();
    }

    @Test
    @DisplayName("생년월일의 최대 길이(10)를 초과하면 BirthDateLengthException이 발생해야 합니다.")
    void birthDateConstructor_TooLongBirthDate_ExceptionThrown() {
        //given
        final String tooLongBirthDate = "11995-11-04";

        //when & then
        assertThatThrownBy(() -> new BirthDate(tooLongBirthDate))
            .isInstanceOf(BirthDateLengthException.class)
            .hasMessageContaining("생년월일의 길이가 최대 길이를 초과했습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    @DisplayName("생년월일이 공백이면 BirthDateBlankException이 발생해야 합니다.")
    void birthConstructor_BlankName_ExceptionThrown(final String blankValue) {
        //when & then
        assertThatThrownBy(() -> new BirthDate(blankValue))
            .isInstanceOf(BirthDateBlankException.class)
            .hasMessageContaining("생년월일은 공백을 제외한 10자리 여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" 2005-05-05", "2005-05-05 ", "  2005-05-05  "})
    @DisplayName("생년월일의 앞뒤 공백이 존재할 경우 해당 공백이 제거되어야 합니다.")
    void birthDateConstructor_BlankBirthDate_ExceptionThrown(final String untrimmedValue) {
        //given
        final BirthDate birthDate = new BirthDate(untrimmedValue);
        final String expectedTrimmedBirthDate = "2005-05-05";

        //then
        assertThat(birthDate.getDate()).isEqualTo(expectedTrimmedBirthDate);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1995-13-45", "2000-02-30", "2005-02-31"})
    @DisplayName("유효하지 않은 생년월일 형식일 경우 예외가 발생해야 합니다.")
    void invalidDateRegex(final String invalidBirthDate) {
        // when & then
        assertThatThrownBy(() -> new BirthDate(invalidBirthDate))
            .isInstanceOf(BirthDateRegexException.class);
    }
}