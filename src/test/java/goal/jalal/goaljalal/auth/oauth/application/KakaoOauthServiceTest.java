package goal.jalal.goaljalal.auth.oauth.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class KakaoOauthServiceTest {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Properties(@JsonProperty("nickname") String nickname,
                             @JsonProperty("thumbnail_image") String thumbnailImage) {

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record UserResponse(String id, Properties properties) {

    }


    @Test
    void test() throws JsonProcessingException {
        String json = "{\"id\":3202384382,\"connected_at\":\"2023-11-29T14:23:02Z\",\"properties\":{\"nickname\":\"김형진\",\"profile_image\":\"http://k.kakaocdn.net/dn/bxZLWW/btsFv0LMPA0/6SMeyXHjaqmYkxaRsKMUK1/img_640x640.jpg\",\"thumbnail_image\":\"http://k.kakaocdn.net/dn/bxZLWW/btsFv0LMPA0/6SMeyXHjaqmYkxaRsKMUK1/img_110x110.jpg\"},\"kakao_account\":{\"profile_nickname_needs_agreement\":false,\"profile_image_needs_agreement\":false,\"profile\":{\"nickname\":\"김형진\",\"thumbnail_image_url\":\"http://k.kakaocdn.net/dn/bxZLWW/btsFv0LMPA0/6SMeyXHjaqmYkxaRsKMUK1/img_110x110.jpg\",\"profile_image_url\":\"http://k.kakaocdn.net/dn/bxZLWW/btsFv0LMPA0/6SMeyXHjaqmYkxaRsKMUK1/img_640x640.jpg\",\"is_default_image\":false,\"is_default_nickname\":false}}}";

        ObjectMapper mapper = new ObjectMapper();
        UserResponse response = mapper.readValue(json, UserResponse.class);

        System.out.println("ID: " + response.id());
        System.out.println("Nickname: " + response.properties.nickname());
        System.out.println("Thumbnail Image URL: " + response.properties.thumbnailImage());
    }


}