package goal.jalal.goaljalal.member.domain;

import goal.jalal.goaljalal.club.domain.Club;
import goal.jalal.goaljalal.club.domain.ClubPost;
import goal.jalal.goaljalal.common.domain.BaseEntity;
import goal.jalal.goaljalal.member.domain.vo.BirthDate;
import goal.jalal.goaljalal.member.domain.vo.Name;
import goal.jalal.goaljalal.participation.domain.ClubJoinRequest;
import goal.jalal.goaljalal.post.domain.Post;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clud_id")
    private Club club;

    @Column(name = "kakao_id", nullable = false, unique = true)
    private long kakaoId;

    @Embedded
    private Name name;

    @Embedded
    private BirthDate birthDate;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Stat stat;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private MemberMatchHistory memberMatchHistory;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ClubJoinRequest> clubJoinRequests = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ClubPost> clubPosts = new ArrayList<>();

    public Member(
        final long kakaoId,
        final Name name,
        final BirthDate birthDate,
        final String profileImageUrl
    ) {
        this.kakaoId = kakaoId;
        this.name = name;
        this.birthDate = birthDate;
        this.profileImageUrl = profileImageUrl;
    }

    public Member(
        final long kakaoId,
        final String name,
        final String birthDate,
        final String profileImageUrl
    ) {
        this(
            kakaoId,
            new Name(name),
            new BirthDate(birthDate),
            profileImageUrl
        );
    }

    public void updateLoginTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

}
