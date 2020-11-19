package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {

    public List<Course> findAllCourses(){
        return DataHolder.courseList;
    }
    public Course findById(Long courseId){
        for (Course course:
             DataHolder.courseList) {
            if (course.getCourseId()==courseId){
                return course;
            }
        }
        return null;
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        for (Course course:
             DataHolder.courseList) {
            if (course.getCourseId()==courseId){
                return course.getStudents();
            }
        }
        return null;
    }

    public Course addStudentToCourse(Course course,Student student){

        course.getStudents().add(student);

        return course;

    }
    public Course getCourseById(Long id){
        return DataHolder.courseList.stream().filter(r->r.getCourseId().equals(id)).findFirst().get();
    }
    public void delete(Long id){
        DataHolder.courseList.removeIf(r->r.getCourseId().equals(id));
    }

    public Optional addCourse(String name, String description, Long teacherId){
        DataHolder.courseList.removeIf(r->r.getName().equals(name));
        List<Student> emptyStudentList = new ArrayList<>();
        Teacher teacher = DataHolder.teacherList.stream().filter(r->r.getId().equals(teacherId)).findFirst().get();
        Course course = new Course(name,description,emptyStudentList,teacher);
        DataHolder.courseList.add(course);
        return Optional.of(course);
    }

}
