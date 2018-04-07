package ca.coursePlanner.model.Courses;

import java.util.ArrayList;
import java.util.List;

public class CourseSections {
    private List<Course> sections = new ArrayList<>();

    public void addSection(Course c) {
        sections.add(c);
    }
}
