package ca.cmpt213.as2.peerfeedbackparser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Represent a group of students
 */
public class Group implements Iterable<Student>{
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);

        // Maintain sorted order
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getEmail().compareToIgnoreCase(o2.getEmail());
            }
        });
    }

    public Student getStudent(String email) {
        for (Student student : students) {
            if (student.matchesEmail(email)) {
                return student;
            }
        }
        return null;
    }

    public boolean containsStudent(String email) {
        return getStudent(email) != null;
    }

    public boolean containsCommentOnStudent(String email) {
        for (Student student : students) {
            if (student.hasCommentOnEmail(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }

    public double getSize() {
        return students.size();
    }
}
