package io.github.alizarion.common.tools.web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author selim@openlinux.fr.
 */
public class AccessControlAllowOriginFilter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        ((HttpServletResponse)resp).addHeader(
                "Access-Control-Allow-Origin",  ((HttpServletRequest) req).getHeader("Origin")

        );

        ((HttpServletResponse)resp).addHeader(
                "Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE"
        );

        ((HttpServletResponse)resp).addHeader(
                "Access-Control-Allow-Headers", "origin, content-type, accept, authorization"
        );

        ((HttpServletResponse)resp).addHeader(
                "Access-Control-Allow-Credentials", "true"
        );
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }


}

