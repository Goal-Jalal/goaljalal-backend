package goal.jalal.goaljalal.member.domain.vo;

import goal.jalal.goaljalal.member.exception.MemberMatchHistoryException.UnknownMemberMatchResult;
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

    @Column(name = "clubWinCount")
    private int clubWinCount = 0;

    @Column(name = "clubTieCount")
    private int clubTieCount = 0;

    @Column(name = "clubLoseCount")
    private int clubLoseCount = 0;

    @Column(name = "individualWinCount")
    private int individualWinCount = 0;

    @Column(name = "individualTieCount")
    private int individualTieCount = 0;

    @Column(name = "individualLoseCount")
    private int individualLoseCount = 0;

    public enum MatchResult {
        CLUB_WIN, CLUB_TIE, CLUB_LOSE, INDIVIDUAL_WIN, INDIVIDUAL_TIE, INDIVIDUAL_LOSE
    }

    public void updateMatchRecord(final MatchResult result) {
        switch (result) {
            case CLUB_WIN:
                clubWinCount++;
                break;
            case CLUB_TIE:
                clubTieCount++;
                break;
            case CLUB_LOSE:
                clubLoseCount++;
                break;
            case INDIVIDUAL_WIN:
                individualWinCount++;
                break;
            case INDIVIDUAL_TIE:
                individualTieCount++;
                break;
            case INDIVIDUAL_LOSE:
                individualLoseCount++;
                break;
            default:
                throw new UnknownMemberMatchResult(result.name());
        }
    }
}
