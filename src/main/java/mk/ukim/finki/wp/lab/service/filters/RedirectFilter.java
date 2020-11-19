package mk.ukim.finki.wp.lab.service.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class RedirectFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
        /*HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String path = request.getServletPath();

        if (("/".equals(path) || ("/AddStudent".equals(path) || ("/StudentEnrollmentSummary".equals(path))
        )) && (request.getSession().getAttribute("course")==null)){
                response.sendRedirect("/listCourses");
            }
        else
        {

        }*/


    }

    @Override
    public void destroy() {

    }
}
