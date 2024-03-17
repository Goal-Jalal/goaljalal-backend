package goal.jalal.goaljalal.auth.oauth.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Properties(@JsonProperty("nickname") String nickname,
                         @JsonProperty("thumbnail_image") String thumbnailImage) {

}
