package ca.coursePlanner.model.Section;

public class Section_ {
    private String semesterCode;
    private String subject;
    private String catalogNumber;
    private String location;
    private String enrollmentCap;
    private String enrollmentTotal;
    private String instructors;
    private String type;
    private String term;
    private int year;
    private long courseOfferingId;

    public Section_(
            String semesterCode,
            String Subject,
            String CatalogNumber,
            String Location,
            String enrollmentCap,
            String enrollmentTotal,
            String Instructors,
            String type,
            long courseOfferingId
    ) {
        this.semesterCode = semesterCode;
        this.subject = Subject;
        this.catalogNumber = CatalogNumber;
        this.location = Location;
        this.enrollmentCap = enrollmentCap;
        this.enrollmentTotal = enrollmentTotal;
        this.instructors = Instructors;
        this.type = type;
        this.courseOfferingId = courseOfferingId;

//        1 for spring, 4 for summer and 7 for fall
        switch (semesterCode.charAt(3)) {
            case '1':
                this.term = "SPRING";
                break;
            case '4':
                this.term = "SUMMER";
                break;
            case '7':
                this.term = "FALL";
                break;
        }

    }

    public long getCourseOfferingId() {
        return courseOfferingId;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String SEMESTER) {
        this.semesterCode = SEMESTER;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String SUBJECT) {
        this.subject = SUBJECT;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String CATALOGNUMBER) {
        this.catalogNumber = CATALOGNUMBER;
    }

    public String getLocation() { return location; }

    public void setLocation(String LOCATION) { this.location = LOCATION; }

    public String getEnrollmentCap() { return enrollmentCap; }

    public void setEnrollmentCap(String ENROLMENTCAPACITY) { this.enrollmentCap = ENROLMENTCAPACITY; }

    public String getEnrollmentTotal() { return enrollmentTotal; }

    public void setEnrollmentTotal(String ENROLMENTTOTAL) { this.enrollmentTotal = ENROLMENTTOTAL; }

    public String getInstructors() {
        return instructors;
    }

    public void setInstructors(String instructors) {
        this.instructors = instructors;
    }

    public String getType() { return type; }

    public void setType(String COMPONENTCODE) { this.type = COMPONENTCODE; }

    public String getTerm() {
        return term;
    }
}
