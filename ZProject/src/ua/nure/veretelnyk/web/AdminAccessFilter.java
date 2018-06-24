package ua.nure.veretelnyk.web;

import ua.nure.veretelnyk.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AdminAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String cmdName = servletRequest.getParameter("command");

        if("get_page".equals(cmdName) && "admin".equals(servletRequest.getParameter("page"))){
            int role = (int) httpRequest.getSession().getAttribute("userRoleId");
            if (role != 1)
                servletRequest.getRequestDispatcher(Path.PAGE_ERROR)
                        .forward(servletRequest, servletResponse);

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
