package com.example.chaitanya.mychat.UploadResume;

/**
 * Created by Chaitanya on 03-04-2018.
 */

public class UploadResumes {
    public String name;
    public String url;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public UploadResumes() {
    }

    public UploadResumes(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
