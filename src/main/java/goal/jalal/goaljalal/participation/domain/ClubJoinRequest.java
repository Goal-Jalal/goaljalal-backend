package goal.jalal.goaljalal.participation.domain;

import goal.jalal.goaljalal.club.domain.Club;
import goal.jalal.goaljalal.global.domain.BaseEntity;
import goal.jalal.goaljalal.member.domain.Member;
import goal.jalal.goaljalal.participation.domain.vo.RequestStatus;
import jakarta.persistence.Column;
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
@Table(name = "clubJoinRequest")
@Entity
public class ClubJoinRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RequestStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    public ClubJoinRequest(
        final RequestStatus status,
        final Member member,
        final Club club
    ) {
        this.status = status;
        this.member = member;
        this.club = club;
    }
}
