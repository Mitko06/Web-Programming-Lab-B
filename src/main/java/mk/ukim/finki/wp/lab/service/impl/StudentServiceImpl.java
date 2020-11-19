package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> listAll() {
        return DataHolder.studentList;
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return null;
    }

    @Override
    public Optional save(String username, String password, String name, String surname) {
        return Optional.of(studentRepository.save(username,password,name,surname));
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentRepository.findStudentByUsername(username);
    }
}
