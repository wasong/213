package ca.coursePlanner.model;

import java.util.List;

public class Subject {
    private List<Course> list;
    private String subject;

    public Subject(List<Course> list, String subject) {
        this.list = list;
        this.subject = subject;
    }
}
