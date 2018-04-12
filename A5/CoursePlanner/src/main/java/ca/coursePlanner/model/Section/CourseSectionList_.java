package ca.coursePlanner.model.Section;

import java.util.ArrayList;
import java.util.List;

public class CourseSectionList_ {
    private List<SectionList_> courseSections = new ArrayList<>();

    public SectionList_ getSectionListByCourseId(long courseId) {
        for (SectionList_ s : courseSections) {
            if (s.getCourseId() == courseId) return s;
        }
        return null;
    }

    public void addSectionList(SectionList_ sectionList) {
        courseSections.add(sectionList);
    }

}
