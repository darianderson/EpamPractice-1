package ua.nure.veretelnyk.web;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AdminAccessFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AdminAccessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOG.debug("Filter init");
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
        LOG.debug("filter is processed fine");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOG.debug("Filter destroy");
    }
}
