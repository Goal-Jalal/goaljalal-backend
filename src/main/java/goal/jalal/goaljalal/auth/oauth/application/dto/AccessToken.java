package goal.jalal.goaljalal.auth.oauth.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccessToken(@JsonProperty("access_token") String accessToken) {

}
