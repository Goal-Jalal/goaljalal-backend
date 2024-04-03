package goal.jalal.goaljalal.auth.oauth.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoOauthProperties {

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

    @Value("${oauth.kakao.grant_type}")
    private String grantType;

}
