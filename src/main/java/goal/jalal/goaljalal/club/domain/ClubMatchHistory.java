package goal.jalal.goaljalal.club.domain;

import goal.jalal.goaljalal.club.domain.vo.MatchForm;
import goal.jalal.goaljalal.club.domain.vo.MatchResult;
import goal.jalal.goaljalal.club.domain.vo.Score;
import goal.jalal.goaljalal.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "club_match_history")
@Entity
public class ClubMatchHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "opponent_clubName")
    private String opponentClubName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "match_result")
    private MatchResult matchResult;

    @Embedded
    private Score score;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "match_form")
    private MatchForm matchForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clud_id")
    private Club club;

    public ClubMatchHistory(
        final String opponentClubName,
        final MatchResult matchResult,
        final Score score,
        final MatchForm matchForm,
        final Club club
    ) {
        this.opponentClubName = opponentClubName;
        this.matchResult = matchResult;
        this.score = score;
        this.matchForm = matchForm;
        this.club = club;
    }
}
