package ca.coursePlanner.controllers;

import ca.coursePlanner.model.About;
import ca.coursePlanner.model.Courses.Course;
import ca.coursePlanner.model.Courses.CourseList;
import ca.coursePlanner.model.Departments.Department;
import ca.coursePlanner.model.Departments.DepartmentList;
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
    private List<Course> courses = new ArrayList<>(); // all courses for grouping purposes
    private AtomicLong nextId = new AtomicLong();

    // TODO: make subjects into class
    private DepartmentList departmentList = new DepartmentList();

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

                courses.add(createCourse(course));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Course createCourse(String[] course) {
        return new Course(course[0], course[1], course[2], course[3], course[4], course[5], course[6], course[7]);
    }

    private void groupCourses() {
        for (Course c : courses) {
            String name = c.getSubject();

            Department queryDept = departmentList.doesDeptExist(name);
            if (queryDept == null) {
                long deptId = nextId.incrementAndGet();
                Department dept = new Department(name, deptId);
                departmentList.addDepartment(dept);
            } else {
                queryDept.addCourse(c);
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
    public List<Department> departments() {
        return departmentList.getDepartments();
    }

    @GetMapping("/api/departments/{id}/courses")
    public List<CourseList> getDeptCourses(@PathVariable("id") long deptId) {
        Department dept = departmentList.findDepartmentById(deptId);

        if (dept == null) throw new IllegalArgumentException();

        return dept.getAllCourses();
    }

    // Create Exception Handle
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
                reason = "Request ID not found.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {
        // Nothing to do
    }
}
