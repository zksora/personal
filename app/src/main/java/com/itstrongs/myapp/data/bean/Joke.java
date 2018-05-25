package com.itstrongs.myapp.data.bean;

/**
 * Created by itstrongs on 2017/12/18.
 */
public class Joke {

    private String imageUrl;
    private String content;

    public Joke(String imageUrl, String content) {
        this.imageUrl = imageUrl;
        this.content = content;
    }

    public Joke() {}
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "imageUrl='" + imageUrl + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
