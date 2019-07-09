package es.alfatec.web.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class LoginPost implements Serializable{
    private String username;
    private String password;
    @SerializedName("domain_web")
    private String domainWeb;

    public LoginPost() {
        
    }
    
    public LoginPost(String username, String password, String domainWeb) {
        this.username = username;
        this.password = password;
        this.domainWeb = domainWeb;
    }
    
    

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDomainWeb() {
        return domainWeb;
    }
    public void setDomainWeb(String domainWeb) {
        this.domainWeb = domainWeb;
    }

    @Override
    public String toString() {
        return "LoginPost{" + "username=" + username + ", password=" + password + ", domainWeb=" + domainWeb + '}';
    }
}
