package com.example.chaitanya.mychat.Analysis;

public class FeedbackModel {
    public String cmp_name, cmp_email, feedback,type;

    public FeedbackModel(String cmp_name, String cmp_email, String feedback, String type) {
        this.cmp_name = cmp_name;
        this.cmp_email = cmp_email;
        this.feedback = feedback;
        this.type = type;
    }

    public String getCmp_name() {
        return cmp_name;
    }

    public void setCmp_name(String cmp_name) {
        this.cmp_name = cmp_name;
    }

    public String getCmp_email() {
        return cmp_email;
    }

    public void setCmp_email(String cmp_email) {
        this.cmp_email = cmp_email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
