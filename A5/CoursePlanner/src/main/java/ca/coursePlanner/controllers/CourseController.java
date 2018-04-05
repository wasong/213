package ca.coursePlanner.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {
    private final String SOURCE_CSV = "/Users/p206849/Desktop/school/213/A5/CoursePlanner/data/course_data_2018.csv";
    private String line = "";
    private String delimiter = ",";

    private List<List<String>> courses = new ArrayList<>();

    public CourseController() {
        // on init parse data
        try (BufferedReader buffer = new BufferedReader(new FileReader(SOURCE_CSV))) {
            while((line = buffer.readLine()) != null) {
                String[] course = line.split(delimiter);

                System.out.println(course.length);

                courses.add(Arrays.asList(course));
            }
        } catch (IOException e) {
            e.printStackTrace();
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
