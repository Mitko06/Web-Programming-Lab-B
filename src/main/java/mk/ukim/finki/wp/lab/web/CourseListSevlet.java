package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="list-courses",urlPatterns = "/listCourses")
public class CourseListSevlet extends HttpServlet {

    private final CourseService courseService;
    private final SpringTemplateEngine templateEngine;

    public CourseListSevlet(CourseService courseService, SpringTemplateEngine templateEngine) {
        this.courseService = courseService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courseList = courseService.listAll();
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("courses",courseList);

        templateEngine.process("listcourses.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Course selectedCourse = courseService.getCourseById(Long.parseLong(req.getParameter("course")));
        req.getSession().setAttribute("course",selectedCourse);
        resp.sendRedirect("/АddStudent");
    }
}