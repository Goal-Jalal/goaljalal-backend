package goal.jalal.goaljalal.club.domain.vo;

import goal.jalal.goaljalal.club.exception.clubmatchhistory.ScoreRangeException;
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

    public static final int MAX_SCORE = 200;
    public static final int MIN_SCORE = 0;

    @Column(name = "home_score")
    private int homeScore;

    @Column(name = "away_score")
    private int awayScore;

    public Score(int homeScore, int awayScore) {
        validateMinMaxScore(homeScore, awayScore);
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public void validateMinMaxScore(final int... values) {
        Arrays.stream(values)
            .filter(score -> score < MIN_SCORE || score > MAX_SCORE)
            .findFirst()
            .ifPresent(score -> {
                throw new ScoreRangeException(score);
            });
    }
}
