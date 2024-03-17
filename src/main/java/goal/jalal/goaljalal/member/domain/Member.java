package goal.jalal.goaljalal.member.domain;

import goal.jalal.goaljalal.global.domain.BaseEntity;
import goal.jalal.goaljalal.member.domain.vo.BirthDate;
import goal.jalal.goaljalal.member.domain.vo.Name;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Stat stat;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private MatchHistory matchHistory;


    @Column(name = "kakaoId", nullable = false, unique = true)
    private long kakaoId;

    @Embedded
    private Name name;

    @Embedded
    private BirthDate birthDate;

    @Column(name = "profileImageUrl")
    private String profileImageUrl;

    @Column(name = "isDeleted")
    private boolean isDeleted = false;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    public Member(
        long kakaoId,
        Name name,
        BirthDate birthDate,
        String profileImageUrl
    ) {
        this.kakaoId = kakaoId;
        this.name = name;
        this.birthDate = birthDate;
        this.profileImageUrl = profileImageUrl;
    }

    public Member(
        long kakaoId,
        String name,
        String birthDate,
        String profileImageUrl
    ) {
        this(
            kakaoId,
            new Name(name),
            new BirthDate(birthDate),
            profileImageUrl
        );
    }
}
