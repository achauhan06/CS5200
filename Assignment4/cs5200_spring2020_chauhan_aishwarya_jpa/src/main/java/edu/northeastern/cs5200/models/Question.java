package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="question")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String question;
    private int points;
    @OneToMany(mappedBy="question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Answer> answers;
    @ManyToOne
    private QuizWidget quizWidget;

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public QuizWidget getQuizWidget() {
        return quizWidget;
    }

    public void setQuizWidget(QuizWidget quizWidget) {
        this.quizWidget = quizWidget;
    }
}
