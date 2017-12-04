/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelBean;

import java.io.IOException;
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
@WebFilter(filterName = "sessionFilter", urlPatterns = {"/*"})
public class sessionFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req1 =(HttpServletRequest)request;
        HttpServletResponse res1 =(HttpServletResponse)response;
       
        String session = (String)req1.getSession().getAttribute("session");
        String currentPath = req1.getRequestURL().toString();
        
       // use = (user) fc.getExternalContext().getSessionMap().get("loggedUser");
        
        if(session != null)
        {
            if(currentPath.contains("index.xhtml"))
            {
                res1.sendRedirect(req1.getContextPath()+"/home.xhtml");
               System.out.println("it is nt empty");
               
               
            }
            else
            {
                chain.doFilter(request, response);
            }
            //System.out.println("it is nt empty");
//            Cookie userCookie = new Cookie(session, (String) request.getAttribute("pnumber"));
//            res1.addCookie(userCookie);
        }
        else
        {
            if(currentPath.contains("home")|| currentPath.contains("profile") || currentPath.contains("transaction"))
            {
                res1.sendRedirect(req1.getContextPath()+"/index.xhtml");
                System.out.println("session destroyed");
            }
            else
            {
                chain.doFilter(request, response);
            }
            //System.out.println("somefin is wrong");
//            String sessionId = session;
//            Cookie userCookie = new Cookie("index.xhtml", sessionId);
//            res1.addCookie(userCookie);
        }
       
//        HttpSession session = req1.getSession(false);
//        String loginURI = req1.getContextPath() + "/index.xhtml";
//        
//        boolean loggedin = session != null && session.getAttribute("session") !=null;
//        boolean loginReq = req1.getRequestURI().equals(loginURI);
//        boolean resourceReq = req1.getRequestURI().startsWith(req1.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);
//        
//        if(loggedin || loginReq || resourceReq)
//        {
//            chain.doFilter(request, response);
//        }
//        else
//        {
//            res1.sendRedirect(loginURI);
//        }
    }

    @Override
    public void destroy() {
        
    }
    
}
