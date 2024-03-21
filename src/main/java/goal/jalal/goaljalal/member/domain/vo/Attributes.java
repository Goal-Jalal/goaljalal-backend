package goal.jalal.goaljalal.member.domain.vo;


import goal.jalal.goaljalal.member.exception.StatException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Arrays;
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

    public Attributes change(final int[] stats) {
        validateStatLengthException(stats);
        validateValueRage(stats);
        validateBalancePoint(stats);

        this.balancePoint = calcBalancePoint(stats);

        setStats(stats);

        return this;
    }

    private int calcBalancePoint(final int[] value) {
        return TOTAL_VALUE - Arrays.stream(value).sum();
    }

    private void setStats(final int[] stats) {
        this.shooting = stats[0];
        this.speed = stats[1];
        this.pass = stats[2];
        this.dribble = stats[3];
        this.defense = stats[4];
        this.health = stats[5];
    }

    private void validateStatLengthException(final int[] value) {
        if (value.length != 6) {
            throw new StatException.StatLengthException(value.length);
        }
    }

    private void validateValueRage(final int[] value) {
        if (Arrays.stream(value).anyMatch(val -> val < MIN_VALUE || val > MAX_VALUE)) {
            throw new StatException.StatRangeException();
        }
    }

    private void validateBalancePoint(final int[] value) {
        final int total = Arrays.stream(value).sum();
        if (total > TOTAL_VALUE) {
            throw new StatException.StatTotalException(total);
        }
    }
}
