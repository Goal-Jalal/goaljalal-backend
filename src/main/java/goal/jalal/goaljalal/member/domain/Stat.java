package goal.jalal.goaljalal.member.domain;

import goal.jalal.goaljalal.member.domain.vo.Attributes;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stat")
@Entity
public class Stat {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Member member;

    @Embedded
    private Attributes attributes = new Attributes();

    public Stat(Long id, Member member) {
        this.id = id;
        this.member = member;
    }
}
