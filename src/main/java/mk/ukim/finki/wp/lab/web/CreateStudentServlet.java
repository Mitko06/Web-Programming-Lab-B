package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="CreateStudentServlet", urlPatterns="/createStudent")
public class CreateStudentServlet extends HttpServlet {
    public final SpringTemplateEngine templateEngine;
    public final StudentService studentService;

    public CreateStudentServlet(SpringTemplateEngine templateEngine, StudentService studentService) {
        this.templateEngine = templateEngine;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp, req.getServletContext());
        templateEngine.process("createstudent.html", context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        studentService.save(username,password,name,surname);

        resp.sendRedirect("/AddStudent");



    }
}
