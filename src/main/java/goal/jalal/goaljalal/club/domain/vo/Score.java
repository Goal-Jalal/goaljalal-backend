package goal.jalal.goaljalal.club.domain.vo;

import goal.jalal.goaljalal.club.exception.ClubMatchHistoryException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Score {

    public static final int MAX_SCORE = 100;
    public static final int MIN_SCORE = 0;

    @Column(name = "homeScore")
    private int homeScore;

    @Column(name = "awayScore")
    private int awayScore;

    public Score(int homeScore, int awayScore) {
        validateMaxScore(homeScore, awayScore);
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public void validateMaxScore(final int... values) {
        Arrays.stream(values)
            .filter(score -> score < MIN_SCORE || score > MAX_SCORE)
            .findFirst()
            .ifPresent(score -> {
                throw new ClubMatchHistoryException.ScoreRangeException(score);
            });
    }
}
