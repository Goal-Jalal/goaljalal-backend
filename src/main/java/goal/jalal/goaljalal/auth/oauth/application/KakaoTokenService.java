package goal.jalal.goaljalal.auth.oauth.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import goal.jalal.goaljalal.auth.exception.AuthenticationException;
import goal.jalal.goaljalal.auth.oauth.application.dto.OauthMember;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoTokenService {

    private final RestTemplate restTemplate;
    @Value("${oauth.kakao.client_id}")
    private String kakaoClientId;
    @Value("${oauth.kakao.redirect_url}")
    private String redirectUrl;
    @Value("${oauth.kakao.secret_key}")
    private String kakaoSecretKey;
    @Value("${oauth.kakao.accessToken_request_url}")
    private String kakaoRequestUrl;
    @Value("${oauth.kakao.userinfo_request_url}")
    private String kakaoUserInfoRequestUrl;

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String PREFIX_BEARER = "Bearer ";
    private static final String HEADER_TYPE = "Authorization";


    public String fetchAccessToken(String kakaoCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", "application/json");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri", redirectUrl);
        params.add("code", kakaoCode);
        params.add("client_secret", kakaoSecretKey);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(kakaoRequestUrl, request,
            String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> responseBody = objectMapper.readValue(response.getBody(),
                new TypeReference<>() {
                });
            return responseBody.get("access_token");
        } catch (JsonProcessingException e) {
            final String logMessage =
                "카카오 accessToken Json 파싱에 실패했습니다. response body : " + response.getBody();
            throw new AuthenticationException.FailAuthenticationException(logMessage);
        }
    }

    public OauthMember getOauthMember(final String accessToken) {
        ResponseEntity<String> response = fetchOauthMemberInfo(accessToken);

        return parsingOauthMember(response);
    }

    private ResponseEntity<String> fetchOauthMemberInfo(final String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HEADER_TYPE, PREFIX_BEARER + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
            kakaoUserInfoRequestUrl,
            HttpMethod.GET,
            entity,
            String.class);
    }

    private OauthMember parsingOauthMember(final ResponseEntity<String> response) {
        try {
            OauthMember oauthMember = mapper.readValue(response.getBody(), OauthMember.class);
            return oauthMember;
        } catch (JsonProcessingException exception) {
            log.error("kakao oauth data json parsing Exception - ", exception);
            throw new IllegalArgumentException(exception);
        }
    }

}
