package goal.jalal.goaljalal.common.fixtures;

import goal.jalal.goaljalal.member.domain.Member;

public class MemberFixtures {

    /**
     * KAKAO_ID
     */
    public static final Long JOHN_KAKAO_ID = 1341534234123L;
    public static final Long MICHAEL_KAKAO_ID = 4839274582123L;
    public static final Long SARA_KAKAO_ID = 254385738199212L;

    /**
     * NAME
     */
    public static final String JOHN_NAME = "John";
    public static final String MICHAEL_NAME = "Michael";
    public static final String SARA_NAME = "Sara";

    /**
     * BIRTH_DATE
     */
    public static final String JOHN_BIRTH_DATE = "1995-05-20";
    public static final String MICHAEL_BIRTH_DATE = "2000-08-11";
    public static final String SARA_BIRTH_DATE = "2005-10-30";

    /**
     * PROFILE_IMAGE_URL
     */
    public static final String JOHN_PROFILE_IMAGE_URL = "https://img.moco.run/john.jpg";
    public static final String MICHAEL_PROFILE_IMAGE_URL = "https://img.moco.run/michael.png";
    public static final String SARA_PROFILE_IMAGE_URL = "https://img.moco.run/sara.gif";

    /**
     * ENTITY
     */
    public static Member JOHN() {
        return new Member(JOHN_KAKAO_ID, JOHN_NAME, JOHN_BIRTH_DATE, JOHN_PROFILE_IMAGE_URL);
    }

    public static Member MICHAEL() {
        return new Member(MICHAEL_KAKAO_ID, MICHAEL_NAME, MICHAEL_BIRTH_DATE,
            MICHAEL_PROFILE_IMAGE_URL);
    }

    public static Member SARA() {
        return new Member(SARA_KAKAO_ID, SARA_NAME, SARA_BIRTH_DATE, SARA_PROFILE_IMAGE_URL);
    }
}
