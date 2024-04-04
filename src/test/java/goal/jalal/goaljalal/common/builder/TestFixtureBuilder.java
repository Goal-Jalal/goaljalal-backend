package goal.jalal.goaljalal.common.builder;

import goal.jalal.goaljalal.member.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestFixtureBuilder {

    @Autowired
    private BuilderSupporter bs;

    public Member buildMember(final Member member) {
        return bs.memberRepository().save(member);
    }

    public void deleteMember(final Member member) {
        bs.memberRepository().delete(member);
    }
}
