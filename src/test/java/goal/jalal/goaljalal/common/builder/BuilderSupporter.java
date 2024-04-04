package goal.jalal.goaljalal.common.builder;

import goal.jalal.goaljalal.member.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuilderSupporter {

    @Autowired
    private MemberRepository memberRepository;

    public MemberRepository memberRepository() {
        return memberRepository;
    }
}
