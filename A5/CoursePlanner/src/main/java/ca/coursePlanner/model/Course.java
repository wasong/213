package ca.coursePlanner.model;

public class Course {
    private String Semester;
    private String Subject;
    private String CatalogNumber;
    private String Location;
    private String EnrolmentCapacity;
    private String EnrolmentTotal;
    private String Instructors;
    private String ComponentCode;

    public Course(
            String Semester,
            String EnrolmentCapacity,
            String EnrolmentTotal,
            String Subject,
            String CatalogNumber,
            String Location,
            String Instructors,
            String ComponentCode
    ) {
        this.Semester = Semester;
        this.EnrolmentCapacity = EnrolmentCapacity;
        this.EnrolmentTotal = EnrolmentTotal;
        this.Subject = Subject;
        this.CatalogNumber = CatalogNumber;
        this.Location = Location;
        this.Instructors = Instructors;
        this.ComponentCode = ComponentCode;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String SEMESTER) {
        this.Semester = SEMESTER;
    }

    public String getSubjet() {
        return Subject;
    }

    public void setSubject(String SUBJECT) {
        this.Subject = SUBJECT;
    }

    public String getCatalogNumber() {
        return CatalogNumber;
    }

    public void setCatalogNumber(String CATALOGNUMBER) {
        this.CatalogNumber = CATALOGNUMBER;
    }

    public String getLocation() { return Location; }

    public void setLocation(String LOCATION) { this.Location = LOCATION; }

    public String getEnrolmentCapacity() { return EnrolmentCapacity; }

    public void setEnrolmentCapacity(String ENROLMENTCAPACITY) { this.EnrolmentCapacity = ENROLMENTCAPACITY; }

    public String getEnrolmentTotal() { return EnrolmentTotal; }

    public void setEnrolmentTotal(String ENROLMENTTOTAL) { this.EnrolmentTotal = ENROLMENTTOTAL; }

    public String getInstructors() {
        return Instructors;
    }

    public void setInstructors(String instructors) {
        Instructors = instructors;
    }

    public String getComponentCode() { return ComponentCode; }

    public void setComponentCode(String COMPONENTCODE) { this.ComponentCode = COMPONENTCODE; }
}
