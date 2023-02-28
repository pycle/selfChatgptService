package cn.ehai.chatgpt.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ChatGptLog {
    private Integer id;

    private String form;

    private String to;

    private String content;

    @JsonProperty("continuation_id")
    private String continuationId;

    private Integer state;

    @JsonProperty("gmt_create")
    private Date gmtCreate;

    @JsonProperty("gmt_modified")
    private Date gmtModified;

    public ChatGptLog(Integer id, String form, String to, String content, String continuationId, Integer state, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.form = form;
        this.to = to;
        this.content = content;
        this.continuationId = continuationId;
        this.state = state;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public ChatGptLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContinuationId() {
        return continuationId;
    }

    public void setContinuationId(String continuationId) {
        this.continuationId = continuationId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}