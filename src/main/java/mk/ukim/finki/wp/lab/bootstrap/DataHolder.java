package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Student> studentList = new ArrayList<>();
    public static List<Course> courseList = new ArrayList<>();
    public static List<Teacher> teacherList = new ArrayList<>();

    @PostConstruct
    public void init(){

        teacherList.add(new Teacher("Teacher #1","Teacher Surname"));
        teacherList.add(new Teacher("Teacher #2","Teacher Surname"));
        teacherList.add(new Teacher("Teacher #3","Teacher Surname"));
        teacherList.add(new Teacher("Teacher #4","Teacher Surname"));
        teacherList.add(new Teacher("Teacher #5","Teacher Surname"));

        studentList.add(new Student("Mitko06","mitko06", "Mitko","Gurbanski"));
        studentList.add(new Student("Igor","igor", "Igor","Dzambaski"));
        studentList.add(new Student("Daniel","daniel", "Daniel","Causki"));
        studentList.add(new Student("Hristijan","hristijan", "Hristijan","Mazganski"));
        studentList.add(new Student("Blaze","blaze", "Blaze","Cocovski"));

        List<Student> courseEnrolled = new ArrayList<>();

        courseEnrolled.add(studentList.get(1));
        courseEnrolled.add(studentList.get(2));
        courseEnrolled.add(studentList.get(3));

        courseList.add(new Course(
                "Veb Programiranje",
                "Servlets, MVC, Spring Boot, Developing apps",
                courseEnrolled,teacherList.get(0)));

        courseEnrolled.clear();
        courseEnrolled.add(studentList.get(4));
        courseEnrolled.add(studentList.get(3));
        courseEnrolled.add(studentList.get(1));

        courseList.add(new Course(
                "Napreden Veb Dizajn",
                "Developing Apps with Advanced Frontend Technologies",
                courseEnrolled,teacherList.get(1)));

        courseEnrolled.clear();
        courseEnrolled.add(studentList.get(2));
        courseEnrolled.add(studentList.get(3));
        courseEnrolled.add(studentList.get(4));

        courseList.add(new Course(
                "Napredna Interakcija Covek Kompjuter",
                "Developing apps with great UX",
                courseEnrolled,teacherList.get(2)));

        courseEnrolled.clear();
        courseEnrolled.add(studentList.get(3));
        courseEnrolled.add(studentList.get(1));
        courseEnrolled.add(studentList.get(2));

        courseList.add(new Course(
                "Marketing",
                "Market your apps great",
                courseEnrolled,teacherList.get(3)));

        courseEnrolled.clear();
        courseEnrolled.add(studentList.get(3));
        courseEnrolled.add(studentList.get(4));
        courseEnrolled.add(studentList.get(2));

        courseList.add(new Course(
                "Dizajn na IS",
                "Make great architecture for your apps",
                courseEnrolled,teacherList.get(3)));

    }


}
