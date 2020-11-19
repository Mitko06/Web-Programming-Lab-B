package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    public List<Student> findAllStudents(){
        return DataHolder.studentList;
    }

    public List<Student> findAllByNameOrSurname(String text){
        return DataHolder.studentList.stream().filter(r->r.getName().toLowerCase().equals(text.toLowerCase()) || r.getSurname().toLowerCase().equals(text.toLowerCase())).collect(Collectors.toList());
    }


    public Student findStudentByUsername(String username){
        return DataHolder.studentList.stream().filter(r-> r.getUsername().equals(username)).findFirst().get();
    }
    public Optional save(String username, String password, String name, String surname){

        Student newStudent = new Student(username,password,name,surname);
        DataHolder.studentList.add(newStudent);
        return Optional.of(newStudent);
    }

}
