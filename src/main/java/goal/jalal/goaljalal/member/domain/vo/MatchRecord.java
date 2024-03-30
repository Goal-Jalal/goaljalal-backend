package goal.jalal.goaljalal.member.domain.vo;

import goal.jalal.goaljalal.member.exception.membermatchhistory.MemberMatchHistoryException.UnknownMemberMatchResult;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class MatchRecord {

    @Column(name = "club_win_count")
    private int clubWinCount = 0;

    @Column(name = "club_draw_count")
    private int clubDrawCount = 0;

    @Column(name = "club_lose_count")
    private int clubLoseCount = 0;

    @Column(name = "individual_win_count")
    private int individualWinCount = 0;

    @Column(name = "individual_draw_count")
    private int individualDrawCount = 0;

    @Column(name = "individual_lose_count")
    private int individualLoseCount = 0;

    public void updateMatchRecord(final MatchResult result) {
        switch (result) {
            case CLUB_WIN:
                clubWinCount++;
                break;
            case CLUB_DRAW:
                clubDrawCount++;
                break;
            case CLUB_LOSE:
                clubLoseCount++;
                break;
            case INDIVIDUAL_WIN:
                individualWinCount++;
                break;
            case INDIVIDUAL_DRAW:
                individualDrawCount++;
                break;
            case INDIVIDUAL_LOSE:
                individualLoseCount++;
                break;
            default:
                throw new UnknownMemberMatchResult(result.name());
        }
    }
}
