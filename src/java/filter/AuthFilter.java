/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;



import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jvm
 */
public class AuthFilter implements Filter {
    
private final List<String> pathFilters = Arrays.asList(new String[]{"admin"});
    
    public AuthFilter() {
    }    
    


    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();

        int indexLastSlash = uri.lastIndexOf('/');
        int indexQuest = uri.indexOf('?');
        String path="";
        if(indexQuest==-1){
            path = uri.substring(indexLastSlash, uri.length()).substring(1);
        }else{
            path = uri.substring(indexLastSlash, indexQuest).substring(1);
        }
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if(session != null){
            chain.doFilter(request, response);
            return;
        }else{
            if(!pathFilters.contains(path)){
                chain.doFilter(request, response);
                return;
            }
        }
        
        ((HttpServletRequest) request).setAttribute("path", path);
        ((HttpServletRequest) request).getServletContext().getRequestDispatcher("/authForm/login.jsp")
                .forward(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

 
    
}
