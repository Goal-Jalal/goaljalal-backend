package goal.jalal.goaljalal.club.domain.vo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import goal.jalal.goaljalal.club.exception.ClubMatchHistoryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("경기 스코어는 최대 스코어(200)를 초과하면 ScoreRangeException 예외가 발생해야 합니다.")
    void scoreConstructor_TooLargeScore_ExceptionThrown() {
        //given
        final int homeScore = 5;
        final int awayScore = 201;

        //when & then
        assertThatThrownBy(() -> new Score(homeScore, awayScore))
            .isInstanceOf(ClubMatchHistoryException.ScoreRangeException.class)
            .hasMessageContaining("스코어는 0 - 200 사이의 값이어야만 합니다.");
    }

    @Test
    @DisplayName("경기 스코어는 최소 스코어(0)보다 미만이면 ScoreRangeException 예외가 발생해야 합니다.")
    void scoreConstructor_TooSmallScore_ExceptionThrown() {
        //given
        final int homeScore = -1;
        final int awayScore = 10;

        //when & then
        assertThatThrownBy(() -> new Score(homeScore, awayScore))
            .isInstanceOf(ClubMatchHistoryException.ScoreRangeException.class)
            .hasMessageContaining("스코어는 0 - 200 사이의 값이어야만 합니다.");
    }


}