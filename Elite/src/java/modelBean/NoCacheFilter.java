/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelBean;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maxwell
 */
@WebFilter(urlPatterns = {"*.xhtml"}, servletNames = { "Faces Servlet" })
public  class NoCacheFilter implements Filter {
    
    public NoCacheFilter()
    {
        
    }
    
    
        @Override
        public void doFilter(ServletRequest rq, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) rq;
        HttpServletResponse response = (HttpServletResponse) res;
        
        if(!request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER))
        {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0);
        }
    chain.doFilter(rq, res);
}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
         //To change body of generated methods, choose Tools | Templates.
    }
}
