/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import entyty.Group;
import entyty.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jvm
 */
@WebFilter(filterName = "CharsetEncodingFilter", urlPatterns = {"/*"})
public class CharsetEncodingFilter implements Filter {
    
    private static final String FILTERABLE_CONTENT_TYPE="application/x-www-form-urlencoded";
    private static final String ENCODING_DEFAULT = "UTF-8";
    private static final String ENCODING_INIT_PARAM_NAME = "encoding";
    private String encoding;

    public CharsetEncodingFilter() {
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
        String contentType = request.getContentType();
        if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE))
            request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
       if(session != null){
           User regUser = (User) session.getAttribute("regUser");
           List<Group> groups = regUser.getGroups();
           for (Group group : groups) {
               if("ADMINS".equals(group.getGroupName())){
                   httpRequest.setAttribute("role", "ADMIN");
                   break;
               }else  if("USERS".equals(group.getGroupName())){
                   httpRequest.setAttribute("role", "USER");
                   break;
               }else{
                   httpRequest.setAttribute("role", "GUEST");
               } 
           }
       }
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig config) {        
        encoding = config.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null)
            encoding = ENCODING_DEFAULT;
        
    }

    @Override
    public void destroy() {
    }

    
}
