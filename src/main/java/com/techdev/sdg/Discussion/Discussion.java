package com.techdev.sdg.Discussion;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.techdev.sdg.Project.Project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "discussion")
public class Discussion implements Serializable {
    final public static String ID = "id";
    final public static String QUESTION = "question";
    final public static String ANSWER = "answer";
    final public static String PROJECT = "project";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "question", nullable = false, unique = false)
    private String question;

    @Column(name = "answer", nullable = true, unique = false)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "discussion_project",
            joinColumns = {@JoinColumn(name = "discussion_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")})
    @JsonManagedReference
    private Project project;

    public Discussion() {
    }

    public Discussion(String question, Project project) {
        setQuestion(question);
        setProject(project);

    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public Project getProject() {
        return project;
    }

    @Override
    public String toString() {
        return "PrivateSector: {\n" +
                "\tid: " + id + ",\n" +
                "\tquestion: " + question + ",\n" +
                "\tanswer: " + answer + ",\n" +
                "\tproject: " + project + ",\n" +
                '}';
    }

}