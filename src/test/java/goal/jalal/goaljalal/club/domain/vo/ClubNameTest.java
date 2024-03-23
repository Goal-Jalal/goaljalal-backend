package goal.jalal.goaljalal.club.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import goal.jalal.goaljalal.club.exception.ClubException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ClubNameTest {

    @Test
    @DisplayName("클럽 이름이 null이면 NullPointException이 발생해야 합니다.")
    void clubNameConstructor_NullValue_ExceptionThrown() {
        //given
        final String clubNameNull = null;

        //when & then
        assertThatThrownBy(() -> new ClubName(clubNameNull))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("클럽 이름은 null일 수 없습니다.");
    }

    @Test
    @DisplayName("클럽 이름이 최대 길이(20)를 초과하면 NameLengthException이 발생해야 합니다.")
    void clubNameConstructor_TooLongName_ExceptionThrown() {
        //given
        final String tooLongName = "ThisNameIsTooLongToBeValid";

        //when & then
        assertThatThrownBy(() -> new ClubName(tooLongName))
            .isInstanceOf(ClubException.NameLengthException.class)
            .hasMessageContaining("클럽 이름의 길이가 최대 이름 크기를 초과했습니다.");

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    @DisplayName("클럽이름이 공백이면 NameBlankException이 발생해야 합니다.")
    void nameConstructor_BlankName_ExceptionThrown(final String blankValue) {
        //when & then
        assertThatThrownBy(() -> new ClubName(blankValue))
            .isInstanceOf(ClubException.NameBlankException.class)
            .hasMessageContaining("클럽 이름은 공백을 제외한 1자 이상이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" GJL-Club", "GJL-Club ", " GJL-Club ", "   GJL-Club   "})
    @DisplayName("클럽이름의 앞뒤 공백이 존재할 경우 해당 공백이 제거되어야 합니다.")
    void trim_Whitespace_From_MemberName(final String untrimmedValue) {
        //given
        final ClubName clubName = new ClubName(untrimmedValue);
        final String expectedTrimmedName = "GJL-Club";

        // then
        assertThat(clubName.getName()).isEqualTo(expectedTrimmedName);
    }
}