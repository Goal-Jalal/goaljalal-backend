package goal.jalal.goaljalal.auth.oauth.util;

import static org.assertj.core.api.Assertions.assertThat;

import goal.jalal.goaljalal.auth.oauth.configuration.KakaoOauthProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

@SpringBootTest
class HttpUtilsTest {

    @Autowired
    private HttpUtils httpUtils;

    @Autowired
    private KakaoOauthProperties oauthProperties;


    @Test
    @DisplayName("RequestEntity를 정상적으로 생성해야 합니다.")
    void generateRequestEntity_correctlyGeneratesEntity() {
        //given
        final String kakaoCode = "Ek2ksadpwEELsmzc3kre";
        final String expectedHeaderType = "application/json";

        //when
        HttpEntity<MultiValueMap<String, String>> entity = httpUtils.generateRequestEntity(
            kakaoCode);

        //then
        assertThat(entity).isNotNull();
        assertThat(entity.getBody()).isNotNull();
        assertThat(MediaType.APPLICATION_FORM_URLENCODED).isEqualTo(
            entity.getHeaders().getContentType());
        assertThat(expectedHeaderType).isEqualTo(entity.getHeaders().getFirst("Accept"));
        assertThat(kakaoCode).isEqualTo(entity.getBody().getFirst("code"));
    }

    @Test
    @DisplayName("Header를 정상적으로 생성해야 합니다.")
    void generateHeader_correctlySetsHeaders() {
        //given
        final String expectedHeaderType = "application/json";

        //when
        HttpHeaders headers = httpUtils.generateHeader();

        //then
        assertThat(headers).isNotNull();
        assertThat(MediaType.APPLICATION_FORM_URLENCODED).isEqualTo(headers.getContentType());
        assertThat(expectedHeaderType).isEqualTo(headers.getFirst("Accept"));
    }

    @Test
    void generateParams_correctlyGeneratesParams() {
        //given
        final String kakaoCode = "Ek2ksadpwEELsmzc3kre";
        final String expectedGrantType = oauthProperties.getGrantType();
        final String expectedClientId = oauthProperties.getKakaoClientId();
        final String expectedRedirectUri = oauthProperties.getRedirectUrl();
        final String expectedSecretKey = oauthProperties.getKakaoSecretKey();

        //when
        MultiValueMap<String, String> params = httpUtils.generateParams(kakaoCode);

        //then
        assertThat(params).isNotNull();
        assertThat(kakaoCode).isEqualTo(params.getFirst("code"));
        assertThat(expectedGrantType).isEqualTo(params.getFirst("grant_type"));
        assertThat(expectedClientId).isEqualTo(params.getFirst("client_id"));
        assertThat(expectedRedirectUri).isEqualTo(params.getFirst("redirect_uri"));
        assertThat(expectedSecretKey).isEqualTo(params.getFirst("client_secret"));
    }
}