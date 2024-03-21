package goal.jalal.goaljalal.club.domain;

import goal.jalal.goaljalal.club.domain.vo.ClubName;
import goal.jalal.goaljalal.club.domain.vo.LogoImageUrl;
import goal.jalal.goaljalal.global.domain.BaseEntity;
import goal.jalal.goaljalal.member.domain.Member;
import goal.jalal.goaljalal.participation.domain.ClubJoinRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "club")
@Entity
public class Club extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private ClubName clubName;

    @Column(name = "intro", columnDefinition = "text")
    private String intro;

    @Embedded
    private LogoImageUrl logoImageUrl;

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ClubMatchHistory> clubHistories = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ClubJoinRequest> clubJoinRequests = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ClubPost> clubPosts = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "captain_id", unique = true, nullable = false)
    private Member captain;

    public Club(
        ClubName clubName,
        String intro,
        LogoImageUrl logoImageUrl,
        Member captain
    ) {
        this.clubName = clubName;
        this.intro = intro;
        this.logoImageUrl = logoImageUrl;
        this.captain = captain;
    }

    public Club(
        String clubName,
        String intro,
        String logoImageUrl,
        Member captain
    ) {
        this(new ClubName(clubName), intro, new LogoImageUrl(logoImageUrl), captain);
    }
}
