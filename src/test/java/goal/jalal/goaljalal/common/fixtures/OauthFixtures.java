package goal.jalal.goaljalal.common.fixtures;

import goal.jalal.goaljalal.auth.oauth.application.dto.OauthMember;
import goal.jalal.goaljalal.auth.oauth.application.dto.Properties;
import org.springframework.http.ResponseEntity;

public class OauthFixtures {

    /**
     * OAUTH ACCESS TOKEN
     */
    public static final String OAUTH_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJpc3N1ZSI6Imtha2FvIn0.coP_Q3Entyu4PpN3mbDJtYK5c9Y3EtX3L8ZDbkRIIfc";
    public static final String OAUTH_TOKEN_RESPONSE = "{\n"
        + "    \"token_type\": \"bearer\",\n"
        + "    \"access_token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJpc3N1ZSI6Imtha2FvIn0.coP_Q3Entyu4PpN3mbDJtYK5c9Y3EtX3L8ZDbkRIIfc\",\n"
        + "    \"id_token\": \"TestIdToken\",\n"
        + "    \"expires_in\": 7199,\n"
        + "    \"refresh_token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyLCJpc3N1ZSI6Imtha2FvIn0.coP_Q3Entyu4PpN3mbDJtYK5c9Y3EtX3L8ZDbkRIIfc\",\n"
        + "    \"refresh_token_expires_in\": 86399,\n"
        + "    \"scope\": \"profile_image openid profile_nickname\"\n"
        + "}";
    public static final String OAUTH_USER_RESPONSE = "{\"id\":\"1\", \"nickname\":\"John\"}";

    /**
     * ENTITY
     */
    public static final ResponseEntity<String> OAUTH_RESPONSE_ENTITY = ResponseEntity.ok(
        OAUTH_TOKEN_RESPONSE);

    public static OauthMember oauthMember() {
        Properties properties = new Properties("John", "https://img.moco.run/1");
        return new OauthMember(1L, properties);
    }

}
