package goal.jalal.goaljalal.member.domain.vo;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchRecordTest {

    @Test
    @DisplayName("클럽 승리 시 클럽 승리 횟수가 증가해야 합니다.")
    void club_Win_Increment() {
        //given
        MatchRecord record = new MatchRecord();

        //when
        record.updateMatchRecord(MatchResult.CLUB_WIN);

        //then
        assertThat(1).isEqualTo(record.getClubWinCount());
    }

    @Test
    @DisplayName("클럽 무승부 시 클럽 무승부 횟수가 증가해야 합니다.")
    void club_Tie_Increment() {
        //given
        MatchRecord record = new MatchRecord();

        //when
        record.updateMatchRecord(MatchResult.CLUB_Draw);

        //then
        assertThat(1).isEqualTo(record.getClubDrawCount());
    }

    @Test
    @DisplayName("클럽 패배 시 클럽 패배 횟수가 증가해야 합니다.")
    void club_Lose_Increment() {
        //given
        MatchRecord record = new MatchRecord();

        //when
        record.updateMatchRecord(MatchResult.CLUB_LOSE);

        //then
        assertThat(1).isEqualTo(record.getClubLoseCount());
    }

    @Test
    @DisplayName("개인 승리 시 개인 승리 횟수가 증가해야 합니다.")
    void individual_Win_Increment() {
        //given
        MatchRecord record = new MatchRecord();

        //when
        record.updateMatchRecord(MatchResult.INDIVIDUAL_WIN);

        //then
        assertThat(1).isEqualTo(record.getIndividualWinCount());
    }

    @Test
    @DisplayName("개인 무승부 시 개인 무승부 횟수가 증가해야 합니다.")
    void individual_Draw_Increment() {
        //given
        MatchRecord record = new MatchRecord();

        //when
        record.updateMatchRecord(MatchResult.INDIVIDUAL_Draw);

        //then
        assertThat(1).isEqualTo(record.getIndividualDrawCount());
    }

    @Test
    @DisplayName("개인 패배 시 개인 패배 횟수가 증가해야 합니다.")
    void individual_Lose_Increment() {
        //given
        MatchRecord record = new MatchRecord();

        //when
        record.updateMatchRecord(MatchResult.INDIVIDUAL_LOSE);

        //then
        assertThat(1).isEqualTo(record.getIndividualLoseCount());
    }

    @Test
    @DisplayName("매치 결과가 null이면 NullPointException이 발생해야 합니다.")
    void matchResult_Is_Null_ExceptionThrown() {
        //given
        MatchResult matchResult = null;

        //when & then
        MatchRecord record = new MatchRecord();
        assertThatThrownBy(() -> record.updateMatchRecord(matchResult))
            .isInstanceOf(NullPointerException.class);
    }
}