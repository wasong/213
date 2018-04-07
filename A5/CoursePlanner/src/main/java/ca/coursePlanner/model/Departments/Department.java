package ca.coursePlanner.model.Departments;

import ca.coursePlanner.model.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Department {
    private DepartmentCourses courses = new DepartmentCourses();
    private String name;
    private long deptId;

    public Department(String name, long deptId) {
        this.name = name;
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public long getDeptId() {
        return deptId;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
}
