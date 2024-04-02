package goal.jalal.goaljalal.auth.oauth.application;

import static goal.jalal.goaljalal.common.fixtures.OauthFixtures.OAUTH_ACCESS_TOKEN;
import static goal.jalal.goaljalal.common.fixtures.OauthFixtures.OAUTH_RESPONSE_ENTITY;
import static goal.jalal.goaljalal.common.fixtures.OauthFixtures.OAUTH_TOKEN_RESPONSE;
import static goal.jalal.goaljalal.common.fixtures.OauthFixtures.OAUTH_USER_RESPONSE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import goal.jalal.goaljalal.auth.oauth.application.dto.OauthMember;
import goal.jalal.goaljalal.common.fixtures.OauthFixtures;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class KakaoTokenServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private KakaoTokenService kakaoTokenService;

    @Value("${oauth.kakao.accessToken_request_url}")
    private String kakaoRequestUrl;
    @Value("${oauth.kakao.userinfo_request_url}")
    private String kakaoUserInfoRequestUrl;

    @Test
    @DisplayName("kakao의 인가 코드로 AccessToken 발급 받아야 합니다.")
    void fetch_accessToken_At_KakaoServer() throws JsonProcessingException {
        // Given
        final String kakaoCode = "testCode";
        final String expectedAccessToken = OAUTH_ACCESS_TOKEN;
        final String validResponseBody = OAUTH_TOKEN_RESPONSE;
        final ResponseEntity<String> response = OAUTH_RESPONSE_ENTITY;

        lenient().when(restTemplate.postForEntity(eq(kakaoRequestUrl), any(HttpEntity.class),
                eq(String.class)))
            .thenReturn(response);

        when(objectMapper.readValue(eq(validResponseBody), any(TypeReference.class)))
            .thenReturn(Map.of("access_token", expectedAccessToken));

        // When
        final String actualAccessToken = kakaoTokenService.fetchAccessToken(kakaoCode);

        // Then
        assertThat(actualAccessToken).isEqualTo(expectedAccessToken);
    }

    @Test
    @DisplayName("AccessToken으로 사용자 정보를 가져와야 합니다.")
    void get_OauthMember() throws JsonProcessingException {
        // Given
        final String accessToken = "testAccessToken";
        final OauthMember expectedMember = OauthFixtures.oauthMember();
        final String responseBody = OAUTH_USER_RESPONSE;
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.exchange(eq(kakaoUserInfoRequestUrl), eq(HttpMethod.GET),
            any(HttpEntity.class), eq(String.class)))
            .thenReturn(responseEntity);
        when(objectMapper.readValue(eq(responseBody), eq(OauthMember.class)))
            .thenReturn(expectedMember);

        // When
        OauthMember actualMember = kakaoTokenService.getOauthMember(accessToken);

        // Then
        assertThat(actualMember).usingRecursiveComparison().isEqualTo(expectedMember);
    }
}