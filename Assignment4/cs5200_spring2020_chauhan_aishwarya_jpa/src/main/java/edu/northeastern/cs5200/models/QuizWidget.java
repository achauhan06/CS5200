package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class QuizWidget extends Widget {

    @OneToMany(mappedBy = "quizWidget", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Question> questions;

    public QuizWidget() {
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
