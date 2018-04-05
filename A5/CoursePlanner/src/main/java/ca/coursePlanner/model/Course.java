package ca.coursePlanner.model;

import java.util.List;

public class Course {
    private int SEMESTER;
    private String SUBJECT;
    private String CATALOGNUMBER;
    private String LOCATION;
    private int ENROLMENTCAPACITY;
    private int ENROLMENTTOTAL;
    private List<String> INSTRUCTORS;
    private String COMPONENTCODE;

    public Course() {
    }

    public Course(
            int SEMESTER,
            int ENROLMENTCAPACITY,
            int ENROLMENTTOTAL,
            String SUBJECT,
            String CATALOGNUMBER,
            String LOCATION,
            String INSTRUCTORS,
            String COMPONENTCODE
    ) {
        this.SEMESTER = SEMESTER;
        this.ENROLMENTCAPACITY = ENROLMENTCAPACITY;
        this.ENROLMENTTOTAL = ENROLMENTTOTAL;
        this.SUBJECT = SUBJECT;
        this.CATALOGNUMBER = CATALOGNUMBER;
        this.LOCATION = LOCATION;
        // TODO: handle comma separated instructors
        this.COMPONENTCODE = COMPONENTCODE;
    }

    public long getSemester() {
        return SEMESTER;
    }

    public void setSemester(int SEMESTER) {
        this.SEMESTER = SEMESTER;
    }

    public String getSubjet() {
        return SUBJECT;
    }

    public void setSubject(String SUBJECT) {
        this.SUBJECT = SUBJECT;
    }

    public String getCatalogNumber() {
        return CATALOGNUMBER;
    }

    public void setCatalogNumber(String CATALOGNUMBER) {
        this.CATALOGNUMBER = CATALOGNUMBER;
    }

    public String getLocation() { return LOCATION; }

    public void setLocation(String LOCATION) { this.LOCATION = LOCATION; }

    public int getEnrolmentCapacity() { return ENROLMENTCAPACITY; }

    public void setEnrolmentCapacity(int ENROLMENTCAPACITY) { this.ENROLMENTCAPACITY = ENROLMENTCAPACITY; }

    public int getEnrolmentTotal() { return ENROLMENTTOTAL; }

    public void setEnrolmentTotal(int ENROLMENTTOTAL) { this.ENROLMENTTOTAL = ENROLMENTTOTAL; }

    // TODO: instructors

    public String getComponentCode() { return COMPONENTCODE; }

    public void setComponentCode(String COMPONENTCODE) { this.COMPONENTCODE = COMPONENTCODE; }
}
