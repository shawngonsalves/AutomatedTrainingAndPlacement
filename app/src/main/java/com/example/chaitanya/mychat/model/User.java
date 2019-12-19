package com.example.chaitanya.mychat.model;

/**
 * Created by Chaitanya on 01-04-2018.
 */

public class User {
    private int id;
    private int PID;
    private String name;
    private String email;
    private String password;
    private String type;
    private String mono;
    private String dob;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getPID(){
        return PID;
    }

    public void setPID(int PID){
        this.PID=PID;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String  getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getMobileNum(){
        return mono;
    }

    public void setMobileNum(String mono){
        this.mono = mono;
    }
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getDob(){
        return dob;
    }

    public void setDob(String dob){
        this.dob = dob;
    }
}
