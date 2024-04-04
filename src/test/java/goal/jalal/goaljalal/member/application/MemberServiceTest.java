package goal.jalal.goaljalal.member.application;

import static goal.jalal.goaljalal.common.fixtures.MemberFixtures.JOHN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import goal.jalal.goaljalal.common.ServiceTest;
import goal.jalal.goaljalal.common.fixtures.MemberFixtures;
import goal.jalal.goaljalal.member.application.dto.MemberInfoResponse;
import goal.jalal.goaljalal.member.configuration.dto.MemberKakaoIdDto;
import goal.jalal.goaljalal.member.domain.Member;
import goal.jalal.goaljalal.member.domain.MemberRepository;
import goal.jalal.goaljalal.member.exception.member.MemberNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MemberServiceTest extends ServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Nested
    @DisplayName("사용자의 정보")
    class GetMyInformation {

        @Test
        @DisplayName("조회에 성공해야 합니다.")
        void success() {
            //given
            final Member REGISTERED_MEMBER = testFixtureBuilder.buildMember(JOHN());

            //when
            final MemberInfoResponse response = memberService.getMemberInformation(
                new MemberKakaoIdDto(REGISTERED_MEMBER.getKakaoId()));

            // then
            assertSoftly(softly -> {
                softly.assertThat(response.id()).isEqualTo(REGISTERED_MEMBER.getId());
                softly.assertThat(response.name())
                    .isEqualTo(REGISTERED_MEMBER.getName().getValue());
                softly.assertThat(response.profileImageUrl())
                    .isEqualTo(REGISTERED_MEMBER.getProfileImageUrl());
                softly.assertThat(response.kakaoId()).isEqualTo(REGISTERED_MEMBER.getKakaoId());
            });
        }

        @Test
        @DisplayName("존재하지 않는 사용자 카카오 아이디로 조회시 실패해야 합니다.")
        void fail_With_Not_RegisteredMember() {
            //given
            final Member NON_REGISTER_MEMBER = JOHN();

            //when & then
            assertThatThrownBy(() -> memberService.getMemberInformation(
                new MemberKakaoIdDto(NON_REGISTER_MEMBER.getKakaoId())))
                .isInstanceOf(MemberNotFoundException.class)
                .hasMessageContaining("조회한 멤버가 존재하지 않습니다.");
        }
    }

    @Nested
    @DisplayName("사용자의 회원 탈퇴 시")
    class DeleteAccount {

        @Test
        @DisplayName("회원 탈퇴에 성공한다.")
        void success() {
            // given
            final Member REGISTERED_MEMBER = testFixtureBuilder.buildMember(MemberFixtures.JOHN());

            // when & then
            assertDoesNotThrow(() -> memberService.leaveMember(
                new MemberKakaoIdDto(REGISTERED_MEMBER.getKakaoId())));
        }

        @Test
        @DisplayName("없는 회원은 탈퇴를 실패한다.")
        void failIfNotRegisteredMember() {
            // given
            final Member NON_REGISTERED_MEMBER = MemberFixtures.JOHN();

            // when & then
            assertThatThrownBy(() -> memberService.leaveMember(
                new MemberKakaoIdDto(NON_REGISTERED_MEMBER.getKakaoId())))
                .isInstanceOf(MemberNotFoundException.class)
                .hasMessageContaining("조회한 멤버가 존재하지 않습니다.");
        }
    }
}