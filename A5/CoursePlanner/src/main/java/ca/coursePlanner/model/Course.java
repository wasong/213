package ca.coursePlanner.model;

public class Course {
    private int Semester;
    private String Subject;
    private String CatalogNumber;
    private String Location;
    private int EnrolmentCapacity;
    private int EnrolmentTotal;
    private String Instructors;
    private String ComponentCode;

    public Course(
            int Semester,
            int EnrolmentCapacity,
            int EnrolmentTotal,
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
        // TODO: handle comma separated instructors
        this.ComponentCode = ComponentCode;
    }

    public long getSemester() {
        return Semester;
    }

    public void setSemester(int SEMESTER) {
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

    public int getEnrolmentCapacity() { return EnrolmentCapacity; }

    public void setEnrolmentCapacity(int ENROLMENTCAPACITY) { this.EnrolmentCapacity = ENROLMENTCAPACITY; }

    public int getEnrolmentTotal() { return EnrolmentTotal; }

    public void setEnrolmentTotal(int ENROLMENTTOTAL) { this.EnrolmentTotal = ENROLMENTTOTAL; }

    // TODO: instructors

    public String getComponentCode() { return ComponentCode; }

    public void setComponentCode(String COMPONENTCODE) { this.ComponentCode = COMPONENTCODE; }
}
