package goal.jalal.goaljalal.auth.oauth.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import goal.jalal.goaljalal.auth.exception.AuthenticationException.FailAuthenticationException;
import goal.jalal.goaljalal.auth.oauth.application.dto.OauthMember;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JsonUtils {

    private final ObjectMapper objectMapper;

    public String parseAccessToken(String jsonValue) {
        try {
            Map<String, String> responseBody = objectMapper.readValue(jsonValue,
                new TypeReference<>() {
                });
            return responseBody.get("access_token");
        } catch (JsonProcessingException e) {
            final String logMessage =
                "카카오 accessToken Json 파싱에 실패했습니다. response body : " + jsonValue;
            throw new FailAuthenticationException(logMessage);
        }
    }

    public OauthMember parsingOauthMember(String jsonValue) {
        try {
            return objectMapper.readValue(jsonValue, OauthMember.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("kakao oauth data json parsing Exception - ", e);
        }
    }
}