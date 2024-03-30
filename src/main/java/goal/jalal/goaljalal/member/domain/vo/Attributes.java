package goal.jalal.goaljalal.member.domain.vo;


import goal.jalal.goaljalal.member.exception.stat.StatException;
import goal.jalal.goaljalal.member.exception.stat.StatRangeException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class Attributes {

    private static final int MAX_VALUE = 100;
    private static final int MIN_VALUE = 0;
    private static final int TOTAL_VALUE = 450;

    @Column(name = "shooting")
    private int shooting = 50;

    @Column(name = "speed")
    private int speed = 50;

    @Column(name = "pass")
    private int pass = 50;

    @Column(name = "dribble")
    private int dribble = 50;

    @Column(name = "defense")
    private int defense = 50;

    @Column(name = "health")
    private int health = 50;

    @Column(name = "balancePoint")
    private int balancePoint = 150;

    public Attributes change(final HashMap<StatType, Integer> stats) {

        validateNull(stats);

        int[] values = stats.values().stream().mapToInt(Integer::intValue).toArray();

        validateStatLengthException(values);
        validateValueRange(values);
        validateBalancePoint(values);

        this.balancePoint = calcBalancePoint(values);
        setStats(stats);

        return this;
    }

    private void validateNull(HashMap<StatType, Integer> stats) {
        if (Objects.isNull(stats)) {
            throw new NullPointerException("스탯은 null일 수 없습니다.");
        }
    }

    private void validateStatLengthException(final int[] value) {
        if (value.length != 6) {
            throw new StatException.StatLengthException(value.length);
        }
    }

    private void validateValueRange(final int[] values) {
        boolean isOutOfRange = Arrays.stream(values)
            .anyMatch(value -> value < MIN_VALUE || value > MAX_VALUE);

        if (isOutOfRange) {
            throw new StatRangeException();
        }
    }

    private void validateBalancePoint(final int[] value) {
        final int total = Arrays.stream(value).sum();
        if (total > TOTAL_VALUE) {
            throw new StatException.StatTotalException(total);
        }
    }

    private void setStats(final HashMap<StatType, Integer> stats) {
        this.shooting = stats.get(StatType.SHOOTING);
        this.speed = stats.get(StatType.SPEED);
        this.pass = stats.get(StatType.PASS);
        this.dribble = stats.get(StatType.DRIBBLE);
        this.defense = stats.get(StatType.DEFENSE);
        this.health = stats.get(StatType.HEALTH);
    }

    private int calcBalancePoint(final int[] value) {
        return TOTAL_VALUE - Arrays.stream(value).sum();
    }
}
