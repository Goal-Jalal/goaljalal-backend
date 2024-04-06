package goal.jalal.goaljalal.member.application;

import goal.jalal.goaljalal.member.application.dto.MemberInfoResponse;
import goal.jalal.goaljalal.member.configuration.dto.MemberIdDto;
import goal.jalal.goaljalal.member.domain.Member;
import goal.jalal.goaljalal.member.domain.MemberRepository;
import goal.jalal.goaljalal.member.exception.member.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberInfoResponse getMemberInformation(final MemberIdDto memberIdDto) {
        final Long memberKakaoId = memberIdDto.kakaoId();

        final Member member = memberRepository.findByKakaoId(memberKakaoId)
            .orElseThrow(() -> new MemberNotFoundException(memberKakaoId));
        return MemberInfoResponse.of(member);
    }

    public void leaveMember(final MemberIdDto memberIdDto) {
        final Long memberKakaoId = memberIdDto.kakaoId();

        final Member member = memberRepository.findByKakaoId(memberKakaoId)
            .orElseThrow(() -> new MemberNotFoundException(memberKakaoId));

        member.leave();
        log.info(String.format("사용자 회원 탈퇴 - request info { kakaoId : %d }", memberKakaoId));
    }
}
