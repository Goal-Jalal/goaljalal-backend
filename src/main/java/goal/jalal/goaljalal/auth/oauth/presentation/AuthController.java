package goal.jalal.goaljalal.auth.oauth.presentation;

import goal.jalal.goaljalal.auth.oauth.application.KakaoOauthService;
import goal.jalal.goaljalal.auth.oauth.application.dto.TokenResponse;
import goal.jalal.goaljalal.auth.oauth.presentation.dto.KakaoOauthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final KakaoOauthService kakaoOauthService;

    @PostMapping("/oauth/kakao/login")
    public ResponseEntity<TokenResponse> login(@RequestBody KakaoOauthToken kakaoOauthToken) {
        TokenResponse token = kakaoOauthService.createToken(kakaoOauthToken.oauthAccessToken());
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
}
