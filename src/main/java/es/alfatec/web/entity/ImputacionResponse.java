package es.alfatec.web.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ImputacionResponse implements Serializable{
    private String status;
    @SerializedName("status_code")
    private int statusCode;
    private String message;
    
    public ImputacionResponse() {
        
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
