package goal.jalal.goaljalal.auth.oauth.util;

import goal.jalal.goaljalal.auth.oauth.configuration.KakaoOauthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RequiredArgsConstructor
@Component
public class HttpUtils {

    private final KakaoOauthProperties oauthProperties;
    private static final String ACCEPT_HEADER = "application/json";

    public HttpEntity<MultiValueMap<String, String>> generateRequestEntity(final String kakaoCode) {

        HttpHeaders headers = generateHeader();
        MultiValueMap<String, String> params = generateParams(kakaoCode);

        return new HttpEntity<>(params, headers);
    }

    public HttpHeaders generateHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", ACCEPT_HEADER);
        return headers;
    }

    public MultiValueMap<String, String> generateParams(final String kakaoCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", oauthProperties.getGrantType());
        params.add("client_id", oauthProperties.getKakaoClientId());
        params.add("redirect_uri", oauthProperties.getRedirectUrl());
        params.add("code", kakaoCode);
        params.add("client_secret", oauthProperties.getKakaoSecretKey());
        return params;
    }
}
