package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="add-student",urlPatterns = "/AddStudent")
public class ListStudentServlet extends HttpServlet {

    private final StudentService studentService;
    private final SpringTemplateEngine templateEngine;
    private final CourseService courseService;

    public ListStudentServlet(StudentService studentService, SpringTemplateEngine templateEngine, CourseService courseService) {
        this.studentService = studentService;
        this.templateEngine = templateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentService.listAll();
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("students",studentList);
        context.setVariable("course",req.getSession().getAttribute("course"));

        templateEngine.process("liststudents.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student choosenStudent = (Student) studentService.getStudentByUsername(req.getParameter("student"));
        Course choosenCourse = (Course) req.getSession().getAttribute("course");

          courseService.addStudentToCourse(choosenStudent,choosenCourse);
        req.getSession().setAttribute("student",choosenStudent);

        resp.sendRedirect("/StudentEnrollmentSummary");

    }
}