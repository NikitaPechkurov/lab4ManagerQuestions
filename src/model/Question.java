package model;

import javafx.beans.property.SimpleStringProperty;

public class Question {

    SimpleStringProperty id;
    SimpleStringProperty question;
    SimpleStringProperty lastname;
    SimpleStringProperty answer;
    SimpleStringProperty comment;

    public Question(String id, String question, String lastname, String answer, String comment) {
        this.id = new SimpleStringProperty(id);
        this.question = new SimpleStringProperty(question);
        this.lastname = new SimpleStringProperty(lastname);
        this.answer = new SimpleStringProperty(answer);
        this.comment = new SimpleStringProperty(comment);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public SimpleStringProperty idProperty() { return id; }

    public String getQuestion() {
        return question.get();
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public SimpleStringProperty questionProperty() {
        return question;
    }

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public SimpleStringProperty lastnameProperty() {
        return lastname;
    }

    public String getAnswer() {
        return answer.get();
    }

    public void setAnswer(String answer) { this.answer.set(answer); }

    public SimpleStringProperty answerProperty() {
        return answer;
    }

    public String getComment() {
        return comment.get();
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public SimpleStringProperty commentProperty() {
        return comment;
    }

    public String record(){
        return question+", "+lastname+", "+answer+", "+comment;
    }

    public String toString(){return " "+id.get()+", "+question.get()+", "+lastname.get()+", "+answer.get()+", "+comment.get()+". ";}
}
