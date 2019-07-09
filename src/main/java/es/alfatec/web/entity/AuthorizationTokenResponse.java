package es.alfatec.web.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class AuthorizationTokenResponse implements Serializable {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    
    public AuthorizationTokenResponse() {
    }
    public String tokenFormatoHeader() {
        return this.tokenType+" "+this.accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getTokenType() {
        return tokenType;
    }
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Override
    public String toString() {
        return "AuthorizationTokenResponse{" + "accessToken=" + accessToken + ", tokenType=" + tokenType + '}';
    }
    
}
