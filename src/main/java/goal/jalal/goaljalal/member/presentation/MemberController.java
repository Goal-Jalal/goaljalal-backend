package goal.jalal.goaljalal.member.presentation;

import goal.jalal.goaljalal.member.application.MemberService;
import goal.jalal.goaljalal.member.application.dto.MemberInfoResponse;
import goal.jalal.goaljalal.member.configuration.AuthPrincipal;
import goal.jalal.goaljalal.member.configuration.dto.MemberKakaoIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<MemberInfoResponse> getMyInformation(
        @AuthPrincipal final MemberKakaoIdDto memberKakaoIdDto
    ) {
        final MemberInfoResponse memberInfoResponse = memberService.getMemberInformation(
            memberKakaoIdDto);

        return ResponseEntity.ok(memberInfoResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(
        @AuthPrincipal final MemberKakaoIdDto memberKakaoIdDto
    ) {
        memberService.leaveMember(memberKakaoIdDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
