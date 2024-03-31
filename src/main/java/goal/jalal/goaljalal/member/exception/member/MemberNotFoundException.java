package goal.jalal.goaljalal.member.exception.member;

import goal.jalal.goaljalal.common.exception.CustomNotFoundException;

public class MemberNotFoundException extends CustomNotFoundException {

    public MemberNotFoundException(final Long kakaoId) {
        super(String.format("조회한 멤버가 존재하지 않습니다. - request info { kakaoId : %d }", kakaoId));
    }
}
