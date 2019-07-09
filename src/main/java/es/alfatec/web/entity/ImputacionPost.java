package es.alfatec.web.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ImputacionPost implements Serializable{
    private String date;
    @SerializedName("effective_time")
    private String effectiveTime;
    @SerializedName("time_from")
    private String timeFrom;
    @SerializedName("time_to")
    private String timeTo;
    
    public ImputacionPost() {
    }
    public ImputacionPost(String date, String effectiveTime, String timeFrom, String timeTo) {
        this.date = date;
        this.effectiveTime = effectiveTime;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getEffectiveTime() {
        return effectiveTime;
    }
    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
    public String getTimeFrom() {
        return timeFrom;
    }
    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }
    public String getTimeTo() {
        return timeTo;
    }
    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }
    @Override
    public String toString() {
        return "ImputacionPost{" + "date=" + date + ", effectiveTime=" + effectiveTime + ", timeFrom=" + timeFrom + ", timeTo=" + timeTo + '}';
    }
}
