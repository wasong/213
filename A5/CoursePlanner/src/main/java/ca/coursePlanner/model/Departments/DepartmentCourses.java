package ca.coursePlanner.model.Departments;

import ca.coursePlanner.model.Courses.Course;
import ca.coursePlanner.model.Courses.CourseList;
import ca.coursePlanner.model.Courses.CourseSections;

import java.util.*;

public class DepartmentCourses {
    private Map<String, CourseSections> courses = new HashMap<>();

    public void add(Course course) {
        String courseNum = course.getCatalogNumber();
        CourseSections section = courses.get(courseNum);

        if (section == null) {
            CourseSections newSection = new CourseSections();
            newSection.addSection(course);
            courses.put(courseNum, newSection);
        } else {
            section.addSection(course);
        }

    }

    public List<CourseList> getAllCourses() {
        Set<String> courseNums = courses.keySet();

        List<CourseList> courses = new ArrayList<>();
        courseNums.forEach(num -> courses.add(new CourseList(num)));

        return courses;
    }
}
