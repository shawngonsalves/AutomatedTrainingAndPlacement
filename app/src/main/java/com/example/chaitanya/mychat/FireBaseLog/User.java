package com.example.chaitanya.mychat.FireBaseLog;

public class User {
    public String name, email, student_id,dob;

    public User(){

    }

    public User(String name, String email, String student_id, String dob) {
        this.name = name;
        this.email = email;
        this.student_id = student_id;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
