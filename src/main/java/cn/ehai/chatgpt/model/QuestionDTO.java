package cn.ehai.chatgpt.model;

public class QuestionDTO {
    private String question;
    private String userinfo;
    public QuestionDTO() {
        this.question = "hello?";
        this.userinfo = "testing";
    }

    {
        this.question = "hello?";
        this.userinfo = "testing";
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "question='" + question + '\'' +
                ", userinfo='" + userinfo + '\'' +
                '}';
    }
}
