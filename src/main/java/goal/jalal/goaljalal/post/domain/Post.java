package goal.jalal.goaljalal.post.domain;

import goal.jalal.goaljalal.club.domain.vo.MatchForm;
import goal.jalal.goaljalal.common.domain.BaseEntity;
import goal.jalal.goaljalal.member.domain.Member;
import goal.jalal.goaljalal.post.domain.vo.Address;
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
@Table(name = "post")
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "matchForm")
    private MatchForm matchForm;

    @Column(name = "content", columnDefinition = "text", nullable = true)
    private String content;

    @Column(name = "schedule")
    private String schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Post(
        final Address address,
        final MatchForm matchForm,
        final String content,
        final String schedule,
        final Member member
    ) {
        this.address = address;
        this.matchForm = matchForm;
        this.content = content;
        this.schedule = schedule;
        this.member = member;
    }

    public Post(
        final String city,
        final String county,
        final String district,
        final MatchForm matchForm,
        final String content,
        final String schedule,
        final Member member
    ) {
        this(
            new Address(city, county, district),
            matchForm,
            content,
            schedule,
            member
        );
    }

}
