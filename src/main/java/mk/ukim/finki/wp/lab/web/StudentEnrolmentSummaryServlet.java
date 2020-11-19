package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="student-enrollment-summary", urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrolmentSummaryServlet extends HttpServlet {

    public final StudentService studentService;
    public final TemplateEngine templateEngine;
    public final CourseService courseService;

    public StudentEnrolmentSummaryServlet(StudentService studentService, TemplateEngine templateEngine, CourseService courseService) {
        this.studentService = studentService;
        this.templateEngine = templateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student choosenStudent = (Student) req.getSession().getAttribute("student");
        Course course = (Course)req.getSession().getAttribute("course");

        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("course",course);

        List<Student> studentListEnrolled = courseService.listStudentsByCourse(course.getCourseId());

        context.setVariable("students",studentListEnrolled);

        templateEngine.process("studentsInCourse.html",context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
