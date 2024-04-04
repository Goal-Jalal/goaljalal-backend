package goal.jalal.goaljalal.member.application.dto;

import goal.jalal.goaljalal.member.domain.Member;

public record MemberInfoResponse(
    Long id,
    Long kakaoId,
    String name,
    String birthDate,
    String profileImageUrl
) {

    public static MemberInfoResponse of(final Member member) {
        return new MemberInfoResponse(
            member.getId(),
            member.getKakaoId(),
            member.getName().getValue(),
            member.getBirthDate().getValue(),
            member.getProfileImageUrl()
        );
    }
}
