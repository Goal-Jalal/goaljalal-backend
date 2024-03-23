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
    private int clubDrawCount = 0;

    @Column(name = "clubLoseCount")
    private int clubLoseCount = 0;

    @Column(name = "individualWinCount")
    private int individualWinCount = 0;

    @Column(name = "individualDrawCount")
    private int individualDrawCount = 0;

    @Column(name = "individualLoseCount")
    private int individualLoseCount = 0;

    public void updateMatchRecord(final MatchResult result) {
        switch (result) {
            case CLUB_WIN:
                clubWinCount++;
                break;
            case CLUB_Draw:
                clubDrawCount++;
                break;
            case CLUB_LOSE:
                clubLoseCount++;
                break;
            case INDIVIDUAL_WIN:
                individualWinCount++;
                break;
            case INDIVIDUAL_Draw:
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
