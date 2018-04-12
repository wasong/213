package ca.coursePlanner.model.Course;

public class Course_ {
    private long courseId;
    private long deptId;
    private String catalogNumber;

    public Course_(long courseId, long deptId, String catalogNumber) {
        this.courseId = courseId;
        this.deptId = deptId;
        this.catalogNumber = catalogNumber;
    }

    public long getDeptId() {
        return deptId;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public long getCourseId() {
        return courseId;
    }
}
