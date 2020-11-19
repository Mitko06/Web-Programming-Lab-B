package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentToCourse(Student student, Course course);
    List<Course> listAll();
    Course getCourseById(Long id);
    void delete(Long id);
    Optional addCourse(String name, String description, Long teacherId);
}
