package ca.coursePlanner.model.Departments;

import ca.coursePlanner.model.Course;

import java.util.ArrayList;
import java.util.List;

public class DepartmentCourses {
    private List<Course> courses = new ArrayList<>();

    public void add(Course course) {
        courses.add(course);
    }
}
