package ca.coursePlanner.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseList_ {
    private List<Course_> courseList = new ArrayList<>();
    private long deptId;

    public CourseList_(long deptId) {
        this.deptId = deptId;
    }

    public long getDeptId() {
        return deptId;
    }

    public List<Course_> getCourseList() {
        return courseList;
    }

    public Course_ getCourseByCourseId(long courseId) {
        for (Course_ c : courseList) {
            if (c.getCourseId() == courseId) return c;
        }
        return null;
    }

    public Course_ getCourseByName(String catalogNumber) {
        for (Course_ c : courseList) {
            if (c.getCatalogNumber().equals(catalogNumber)) return c;
        }
        return null;
    }

    public void addCourse(Course_ c) {
        courseList.add(c);
    }
}
