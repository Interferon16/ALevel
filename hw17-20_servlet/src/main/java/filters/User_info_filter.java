package filters;

import DBManaging.DBManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/userinfo.jsp")
public class User_info_filter implements Filter {

    DBManager dbManager = new DBManager();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie[] cookie = request.getCookies();

        boolean true_user = false;

        for (Cookie c : cookie) {
            if (c.getName().equals("user_id") && dbManager.isAlreadyExists("id", c.getValue())) {
                true_user = true;

            }
        }
        if(true_user){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            response.sendRedirect("/index.html");
        }
    }



    @Override
    public void destroy() {

    }
}
