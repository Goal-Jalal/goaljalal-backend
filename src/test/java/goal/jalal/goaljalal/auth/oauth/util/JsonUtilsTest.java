package goal.jalal.goaljalal.auth.oauth.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import goal.jalal.goaljalal.auth.exception.AuthenticationException.FailAuthenticationException;
import goal.jalal.goaljalal.auth.oauth.application.dto.AccessToken;
import goal.jalal.goaljalal.auth.oauth.application.dto.OauthMember;
import goal.jalal.goaljalal.common.fixtures.OauthFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JsonUtilsTest {

    @Autowired
    private JsonUtils jsonUtils;

    @Test
    @DisplayName("accessToken 파싱에 성공해야 합니다.")
    void parse_AccessToken() {
        //given
        final String jsonValue = "{\"access_token\":\"test_token\"}";
        final String expectedValue = "test_token";

        //when
        final AccessToken result = jsonUtils.extractAccessToken(jsonValue);

        //then
        assertThat(result.accessToken()).isEqualTo(expectedValue);
    }

    @Test
    @DisplayName("accessToken 파싱에 실패했을때 FailAuthenticationException이 발생해야 합니다.")
    void parse_AccessToken_ExceptionThrown() {
        //given
        final String jsonValue = "{\"access_token\"?\"test_token\"}";
        final String expectedValue = "test_token";

        //when & then
        assertThatThrownBy(() -> jsonUtils.extractAccessToken(jsonValue))
            .isInstanceOf(FailAuthenticationException.class)
            .hasMessageContaining("카카오 accessToken Json 파싱에 실패했습니다. response body :");
    }

    @Test
    @DisplayName("Json 포맷의 유저 정보를 파싱하여 OauthMember 객체 생성을 성공해야 합니다.")
    void parse_UserInfo_To_OauthMember() {
        //given
        final String jsonValue = "{\"id\":1,\"connected_at\":\"2023-11-29T14:23:02Z\",\"properties\":{\"nickname\":\"John\",\"profile_image\":\"https://img.moco.run/1\",\"thumbnail_image\":\"https://img.moco.run/1\"},\"kakao_account\":{\"profile_nickname_needs_agreement\":false,\"profile_image_needs_agreement\":false,\"profile\":{\"nickname\":\"김형진\",\"thumbnail_image_url\":\"http://k.kakaocdn.net/dn/bxZLWW/btsFv0LMPA0/6SMeyXHjaqmYkxaRsKMUK1/img_110x110.jpg\",\"profile_image_url\":\"http://k.kakaocdn.net/dn/bxZLWW/btsFv0LMPA0/6SMeyXHjaqmYkxaRsKMUK1/img_640x640.jpg\",\"is_default_image\":false,\"is_default_nickname\":false}}}";

        final OauthMember expectedMember = OauthFixtures.oauthMember();

        //when
        OauthMember result = jsonUtils.extractOauthMember(jsonValue);

        //then
        assertThat(result).isEqualTo(expectedMember);
    }

    @Test
    @DisplayName("Json 포맷의 유저 정보를 파싱에 실패하면 IllegalArgumentException이 발생해야 합니다.")
    void parse_UserInfo_IllegalArgumentExceptionThrown() {
        //given
        final String jsonValue = "{\"id\"$1,\"connected_at\":\"2023-11-29T14:23:02Z\",\"properties\":{\"nickname\":\"John\",\"profile_image\":\"https://img.moco.run/1\",\"thumbnail_image\":\"https://img.moco.run/1\"},\"kakao_account\":{\"profile_nickname_needs_agreement\":false,\"profile_image_needs_agreement\":false,\"profile\":{\"nickname\":\"김형진\",\"thumbnail_image_url\":\"http://k.kakaocdn.net/dn/bxZLWW/btsFv0LMPA0/6SMeyXHjaqmYkxaRsKMUK1/img_110x110.jpg\",\"profile_image_url\":\"http://k.kakaocdn.net/dn/bxZLWW/btsFv0LMPA0/6SMeyXHjaqmYkxaRsKMUK1/img_640x640.jpg\",\"is_default_image\":false,\"is_default_nickname\":false}}}";

        //when & then
        assertThatThrownBy(() -> jsonUtils.extractOauthMember(jsonValue))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("카카오 유저 정보 파싱에 실패했습니다.");
    }
}