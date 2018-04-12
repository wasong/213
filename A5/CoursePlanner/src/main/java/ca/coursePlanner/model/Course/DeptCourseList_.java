package ca.coursePlanner.model.Course;

import java.util.ArrayList;
import java.util.List;

public class DeptCourseList_ {
    private List<CourseList_> courseLists = new ArrayList<>();

    public CourseList_ getCourseListByDeptId(long deptId) {
        for (CourseList_ c : courseLists) {
            if (c.getDeptId() == deptId) return c;
        }
        return null;
    }

    public void addCourseList(CourseList_ courseList) {
        courseLists.add(courseList);
    }
}
