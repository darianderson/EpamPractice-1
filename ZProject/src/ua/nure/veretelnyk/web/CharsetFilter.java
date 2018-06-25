package ua.nure.veretelnyk.web;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CharsetFilter.class);
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) {
        LOG.debug("Charset filter init");
        encoding = filterConfig.getInitParameter("requestEncoding");
        LOG.debug("Default charset " + encoding);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            LOG.trace("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }

        LOG.debug("Filter finished");
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
        LOG.debug("Charset filter destroy");
    }
}
