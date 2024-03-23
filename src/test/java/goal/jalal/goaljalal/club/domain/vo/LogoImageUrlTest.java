package goal.jalal.goaljalal.club.domain.vo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LogoImageUrlTest {

    @Test
    @DisplayName("로고 이미지 Url이 null이면 NullPointException이 발생해야 합니다.")
    void logoImageUrl_Is_Null_ExceptionThrown() {
        //given
        String logoImageUrlNull = null;

        //when & then
        assertThatThrownBy(() -> new LogoImageUrl(logoImageUrlNull))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("로고 이미지 Url은 null일 수 없습니다.");
    }
}