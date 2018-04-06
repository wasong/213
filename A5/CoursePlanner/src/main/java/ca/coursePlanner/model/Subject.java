package ca.coursePlanner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Subject {
    private HashMap<String, List<Course>> courses = new HashMap<>();
    private String subject;

    public Subject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void addCourse(Course course) {
        String key = course.getCatalogNumber();
        List<Course> courseList;
        boolean exists = this.courses.containsKey(key);

        if (exists) {
            courseList = this.courses.get(key);
            courseList.add(course);
        } else {
            courseList = new ArrayList<>();
            courseList.add(course);
            this.courses.put(key, courseList);
        }
    }

    public List<List<Course>> getAllCourses() {
        List<List<Course>> allCourses = new ArrayList<>();

        this.courses.forEach((k, v) -> allCourses.add(v));

        return allCourses;
    }

    public List<Course> getCourseByCatalog(String catalogNumber) {
        return this.courses.get(catalogNumber);
    }
}
