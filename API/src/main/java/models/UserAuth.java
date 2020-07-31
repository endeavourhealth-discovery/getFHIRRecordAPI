package models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "grantType",
        "clientId",
        "clientSecret"
})
public class UserAuth {

    @JsonProperty("grantType")
    private String grantType;
    @JsonProperty("clientId")
    private String clientId;
    @JsonProperty("clientSecret")
    private String clientSecret;

    @JsonProperty("grantType")
    public String getGrantType() {
        return grantType;
    }
    @JsonProperty("grantType")
    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    @JsonProperty("clientId")
    public String getClientId() {
        return clientId;
    }
    @JsonProperty("clientId")
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @JsonProperty("clientSecret")
    public String getClientSecret() {
        return clientSecret;
    }
    @JsonProperty("clientSecret")
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

}
