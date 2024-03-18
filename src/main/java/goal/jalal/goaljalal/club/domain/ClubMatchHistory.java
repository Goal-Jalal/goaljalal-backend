package goal.jalal.goaljalal.club.domain;

import goal.jalal.goaljalal.club.domain.vo.MatchForm;
import goal.jalal.goaljalal.club.domain.vo.MatchResult;
import goal.jalal.goaljalal.club.domain.vo.Score;
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
@Table(name = "clubMatchHistory")
@Entity
public class ClubMatchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clud_id")
    private Club club;

    @Column(name = "opponentClubName")
    private String opponentClubName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "matchResult")
    private MatchResult matchResult;

    @Embedded
    private Score score;

    @Embedded
    private MatchForm matchForm;

}
