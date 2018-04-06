package ca.coursePlanner.controllers;

import ca.coursePlanner.model.Course;
import ca.coursePlanner.model.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CourseController {
    private List<Course> courses = new ArrayList<>();
    private HashMap<String, Subject> subjects = new HashMap<>();

    public CourseController() {
        this.readCSV();
        this.groupCourses();

        subjects.forEach((k, v) -> System.out.println(v.getSubject()));
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
            String key = c.getSubject();
            Subject val = new Subject(key);

            Subject subject = subjects.putIfAbsent(key, val);
            if (subject != null) {
                // associated
                subject.addCourse(c);
            } else {
                subject = subjects.get(c.getSubject());
                subject.addCourse(c);
            }
        }
    }

    @GetMapping("/api/dump-model")
    public String dumpModel() {
        return "dump model here";
    }

    // Create Exception Handle
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,
                reason = "Request ID not found.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {
        // Nothing to do
    }
}
