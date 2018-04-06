package ca.coursePlanner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Subject {
    private List<Course> list = new ArrayList<>();
    private HashMap<String, List<Course>> courses = new HashMap<>();
    private String subject;

    public Subject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void addCourse(Course course) {
        list.add(course);
    }
}
