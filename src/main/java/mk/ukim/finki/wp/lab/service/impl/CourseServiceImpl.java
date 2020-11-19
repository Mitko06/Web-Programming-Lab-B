package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, StudentRepository studentRepository1) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentToCourse(Student student, Course course) {
        return courseRepository.addStudentToCourse(course,student);
    }


    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses().stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.getCourseById(id);
    }

    @Override
    public void delete(Long id) {
        courseRepository.delete(id);
    }

    @Override
    public Optional addCourse(String name, String description, Long teacherId) {
        return Optional.of(courseRepository.addCourse(name,description,teacherId));
    }
}
