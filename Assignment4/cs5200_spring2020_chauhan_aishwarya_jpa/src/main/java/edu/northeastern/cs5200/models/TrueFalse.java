package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
public class TrueFalse extends Question {

    private boolean isTrue;

    public TrueFalse() {
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }
}
