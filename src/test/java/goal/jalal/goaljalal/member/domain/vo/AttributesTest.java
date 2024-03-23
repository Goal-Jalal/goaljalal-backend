package goal.jalal.goaljalal.member.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import goal.jalal.goaljalal.member.exception.StatException;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AttributesTest {

    @Test
    @DisplayName("스탯이 기본값(50)으로 생성되어야 합니다.")
    void should_Create_Attributes_With_Default_Values() {
        //given
        final Attributes attributes = new Attributes();

        //when & then
        assertThat(attributes.getPass()).isEqualTo(50);
        assertThat(attributes.getHealth()).isEqualTo(50);
        assertThat(attributes.getDefense()).isEqualTo(50);
        assertThat(attributes.getDribble()).isEqualTo(50);
        assertThat(attributes.getShooting()).isEqualTo(50);
        assertThat(attributes.getSpeed()).isEqualTo(50);

        assertThat(attributes.getBalancePoint()).isEqualTo(150);
    }

    @Test
    @DisplayName("스탯이 null이면 NullPointerException이 발생해야 합니다.")
    void stat_NullValue_ExceptionThrown() {
        //given
        final HashMap<StatType, Integer> stat = null;
        final Attributes attributes = new Attributes();

        //when & then
        assertThatThrownBy(() -> attributes.change(stat))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("스탯은 null일 수 없습니다.");
    }

    @Test
    @DisplayName("스탯의 개수가 6개가 아니라면 StatLengthException이 발생해야 합니다.")
    void number_Of_Stats_is_not_6_ExceptionThrown() {
        //given
        final Attributes attributes = new Attributes();
        final HashMap<StatType, Integer> stat = new HashMap<>();
        stat.put(StatType.DEFENSE, 100);
        stat.put(StatType.DRIBBLE, 75);
        stat.put(StatType.PASS, 60);
        stat.put(StatType.SHOOTING, 60);
        stat.put(StatType.SPEED, 60);

        //when & then
        assertThatThrownBy(() -> attributes.change(stat))
            .isInstanceOf(StatException.StatLengthException.class)
            .hasMessageContaining("스탯의 개수는 6개여야 합니다.");
    }

    @Test
    @DisplayName("스탯 값이 최대값(100)을 초과하면 StatRangeException이 발생해야 합니다.")
    void stat_TooLargeValue_ExceptionThrown() {
        //given
        final Attributes attributes = new Attributes();
        final HashMap<StatType, Integer> stat = new HashMap<>();
        stat.put(StatType.DEFENSE, 101);
        stat.put(StatType.DRIBBLE, 75);
        stat.put(StatType.PASS, 60);
        stat.put(StatType.SHOOTING, 60);
        stat.put(StatType.SPEED, 60);
        stat.put(StatType.HEALTH, 60);

        //when & then
        assertThatThrownBy(() -> attributes.change(stat))
            .isInstanceOf(StatException.StatRangeException.class)
            .hasMessageContaining("올바른 스탯 값이 아닙니다.");
    }

    @Test
    @DisplayName("총 스탯 값의 합이 최대값(450)를 초과할 때 StatTotalException이 발생해야 합니다.")
    void stat_TotalValue_TooLarge_ExceptionThrown() {
        //given
        final Attributes attributes = new Attributes();
        final HashMap<StatType, Integer> stat = new HashMap<>();
        stat.put(StatType.DEFENSE, 100);
        stat.put(StatType.DRIBBLE, 100);
        stat.put(StatType.PASS, 100);
        stat.put(StatType.SHOOTING, 90);
        stat.put(StatType.SPEED, 100);
        stat.put(StatType.HEALTH, 100);

        //when & then
        assertThatThrownBy(() -> attributes.change(stat))
            .isInstanceOf(StatException.StatTotalException.class)
            .hasMessageContaining("스탯의 총합은 450을 넘을 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {60, 65, 70, 75})
    @DisplayName("스탯을 변경한 후 모든 스탯 값과 잔여 포인트가 정확하게 계산되는지 확인 합니다.")
    void stat_Change_Calculate_BalancePoint(final int point) {
        //given
        final int total = 450;
        final Attributes attributes = new Attributes();
        final HashMap<StatType, Integer> stat = new HashMap<>();
        stat.put(StatType.DEFENSE, point);
        stat.put(StatType.DRIBBLE, point);
        stat.put(StatType.PASS, point);
        stat.put(StatType.SHOOTING, point);
        stat.put(StatType.SPEED, point);
        stat.put(StatType.HEALTH, point);

        //when
        final Attributes changedAttributes = attributes.change(stat);
        final int expectedBalancePoint = total - (point * 6);

        //then
        assertThat(changedAttributes.getBalancePoint()).isEqualTo(expectedBalancePoint);
    }
}