package cn.ehai.chatgpt.model;


public class ChatGptDTO {
    private Integer errorCode;
    private String message;
    private String result;
    private Boolean success;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
    @Override
    public String toString() {
        return "ChatGptDTO{" +
                "errorCode=" + errorCode +
                ", message='" + message + '\'' +
                ", result='" + result + '\'' +
                ", success=" + success +
                '}';
    }

}
