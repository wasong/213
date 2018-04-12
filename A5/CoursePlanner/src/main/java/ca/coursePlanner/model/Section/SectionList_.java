package ca.coursePlanner.model.Section;

import java.util.ArrayList;
import java.util.List;

public class SectionList_ {
    private List<Section_> sections = new ArrayList<>();
    private long courseId;

    public SectionList_(long courseId) {
        this.courseId = courseId;
    }

    public Section_ getSectionBySectionId(long sectionId) {
        for (Section_ s : sections) {
            if (s.getCourseOfferingId() == sectionId) return s;
        }

        return null;
    }

    public List<Section_> getSections() {
        return sections;
    }

    public void addSection(Section_ section) {
        sections.add(section);
    }

    public long getCourseId() {
        return courseId;
    }
}
