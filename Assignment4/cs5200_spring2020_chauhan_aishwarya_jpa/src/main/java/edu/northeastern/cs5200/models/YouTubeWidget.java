package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
public class YouTubeWidget extends Widget {

    private String youTubeId;

    public YouTubeWidget() {
    }

    public String getYouTubeId() {
        return youTubeId;
    }

    public void setYouTubeId(String youTubeId) {
        this.youTubeId = youTubeId;
    }
}
