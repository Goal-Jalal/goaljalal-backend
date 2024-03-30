package goal.jalal.goaljalal.member.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import goal.jalal.goaljalal.member.exception.MemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @Test
    @DisplayName("멤버 이름이 null이면 NullPointerException이 발생해야 합니다.")
    void nameConstructor_NullValue_ExceptionThrown() {
        //given
        final String nameNull = null;

        //when & then
        assertThatThrownBy(() -> new Name(nameNull))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("멤버 이름은 null일 수 없습니다.");

    }

    @Test
    @DisplayName("멤버 이름이 최대 길이(20)를 초과하면 NameLengthException이 발생해야 합니다.")
    void nameConstructor_TooLongName_ExceptionThrown() {
        //given
        final String tooLongName = "ThisNameIsTooLongToBeValid";

        //when & then
        assertThatThrownBy(() -> new Name(tooLongName))
            .isInstanceOf(MemberException.NameLengthException.class)
            .hasMessageContaining("멤버 이름의 길이가 최대 이름 길이를 초과했습니다.");

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    @DisplayName("멤버 이름이 공백이면 NameBlankException이 발생해야 합니다.")
    void nameConstructor_BlankName_ExceptionThrown(final String blankValue) {
        //when & then
        assertThatThrownBy(() -> new Name(blankValue))
            .isInstanceOf(MemberException.NameBlankException.class)
            .hasMessageContaining("멤버 이름은 공백을 제외한 1자 이상이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" John", "John ", " John ", "   John   "})
    @DisplayName("멤버 이름의 앞뒤 공백이 존재할 경우 해당 공백이 제거되어야 합니다.")
    void trim_Whitespace_From_MemberName(final String untrimmedValue) {
        //given
        final Name name = new Name(untrimmedValue);
        final String expectedTrimmedName = "John";

        // then
        assertThat(name.getValue()).isEqualTo(expectedTrimmedName);
    }

}