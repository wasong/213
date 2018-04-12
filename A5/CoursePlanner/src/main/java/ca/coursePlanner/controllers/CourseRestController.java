package ca.coursePlanner.controllers;

import ca.coursePlanner.model.About;
import ca.coursePlanner.model.Course.CourseList_;
import ca.coursePlanner.model.Course.Course_;
import ca.coursePlanner.model.Course.DeptCourseList_;
import ca.coursePlanner.model.Section.CourseSectionList_;
import ca.coursePlanner.model.Section.SectionList_;
import ca.coursePlanner.model.Section.Section_;
import ca.coursePlanner.model.Departments.Department_;
import ca.coursePlanner.model.Departments.DepartmentList_;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
public class CourseRestController {
    private List<Section_> courses = new ArrayList<>(); // all courses for grouping purposes
    private AtomicLong deptId = new AtomicLong();
    private AtomicLong courseId = new AtomicLong(100);
    private AtomicLong courseOfferingId = new AtomicLong(1000);

    private DepartmentList_ departmentList = new DepartmentList_(); // CMPT, MACM, MATH, ...
    private DeptCourseList_ deptCourseList = new DeptCourseList_(); // CMPT 213, CMPT 225, ...
    private CourseSectionList_ courseSectionList = new CourseSectionList_(); // CMPT 213 LEC, CMPT 213 LAB, ...

    public CourseRestController() {
        this.readCSV();
        this.groupCourses();
        System.out.println(departmentList.getDepartments().size());
    }

    private void readCSV() {
        final String SOURCE_CSV = "/Users/p206849/Desktop/school/213/A5/CoursePlanner/data/course_data_2018.csv";
        String line;
        String delimiter = "[,\"]";

        // on init parse data
        try (BufferedReader buffer = new BufferedReader(new FileReader(SOURCE_CSV))) {
            while((line = buffer.readLine()) != null) {
                String[] course = line.split(delimiter);
                // trim and remove empty tokens
                course = Arrays.stream(course)
                        .filter(x -> !x.equals(""))
                        .map(String::trim)
                        .toArray(String[]::new);

                if (course.length > 8) {
                    // reduce prof name into one
                    String[] profs = Arrays.copyOfRange(course, 6, course.length - 1);
                    String prof = Arrays.stream(profs).collect(Collectors.joining(", "));

                    // create new course data
                    List<String> updatedCourse = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(course, 0, 6)));
                    updatedCourse.add(prof);
                    updatedCourse.add(course[course.length - 1]);

                    course = updatedCourse.toArray(new String[0]);
                }
//                for (String s : course) System.out.print(s + " | ");
//                System.out.println();

                courses.add(createSection(course));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Section_ createSection(String[] course) {
        return new Section_(course[0], course[1], course[2], course[3], course[4], course[5], course[6], course[7], courseOfferingId.incrementAndGet());
    }

    private void groupCourses() {
        for (Section_ c : courses) {
            String name = c.getSubject();

            // create departments
            Department_ queryDept = departmentList.doesDeptExist(name);
            if (queryDept == null) {
                long deptId = this.deptId.incrementAndGet();
                Department_ dept = new Department_(name, deptId);
                queryDept = dept;
                departmentList.addDepartment(dept);
            }

            // create department courses
            CourseList_ queryCourseList = deptCourseList.getCourseListByDeptId(queryDept.getDeptId());
            Course_ queryCourse;
            if (queryCourseList == null) {
                // create courseList
                CourseList_ newCourseList = new CourseList_(queryDept.getDeptId());
                // add first course to this courseList
                Course_ newCourse = new Course_(courseId.incrementAndGet(), queryDept.getDeptId(), c.getCatalogNumber());
                newCourseList.addCourse(newCourse);
                queryCourse = newCourseList.getCourseByName(c.getCatalogNumber());
                deptCourseList.addCourseList(newCourseList);
            } else {
                // courseList exists so check if course exists
                queryCourse = queryCourseList.getCourseByName(c.getCatalogNumber());
                if (queryCourse == null) {
                    // only add if does not exist
                    Course_ newCourse = new Course_(courseId.incrementAndGet(), queryDept.getDeptId(), c.getCatalogNumber());
                    queryCourse = newCourse;
                    queryCourseList.addCourse(newCourse);
                }
            }

            // now handle section
            SectionList_ querySectionList = courseSectionList.getSectionListByCourseId(queryCourse.getCourseId());
            if (querySectionList == null) {
                // no sections for this course
                SectionList_ newSectionList = new SectionList_(queryCourse.getCourseId());
                newSectionList.addSection(c);
//                querySectionList = newSectionList;
                courseSectionList.addSectionList(newSectionList);
            } else {
                // section exists
                querySectionList.addSection(c);
            }

        }
    }

    @GetMapping("/api/dump-model")
    public String dumpModel() {
        return "dump model here";
    }

    @GetMapping("/api/about")
    public About about() {
        return new About("Andrew Song", "CMPT 213 App");
    }

    @GetMapping("/api/departments")
    public List<Department_> departments() {
        return departmentList.getDepartments();
    }

    @GetMapping("/api/departments/{id}/courses")
    public List<Course_> getDeptCourses(@PathVariable("id") long deptId) {
        return deptCourseList.getCourseListByDeptId(deptId).getCourseList();
    }

    @GetMapping("/api/departments/{id}/courses/{courseId}/offerings")
    public List<Section_> getDeptCourses(@PathVariable("id") long deptId, @PathVariable("courseId") long courseId) {
        return courseSectionList.getSectionListByCourseId(courseId).getSections();
    }

    @GetMapping("/api/departments/{id}/courses/{courseId}/offerings/{sectionId}")
    public Section_ getDeptCourses(@PathVariable("id") long deptId, @PathVariable("courseId") long courseId,  @PathVariable("sectionId") long sectionId) {
        return courseSectionList.getSectionListByCourseId(courseId).getSectionBySectionId(sectionId);
    }

    // Create Exception Handle
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
                reason = "Request ID not found.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {
        // Nothing to do
    }
}
