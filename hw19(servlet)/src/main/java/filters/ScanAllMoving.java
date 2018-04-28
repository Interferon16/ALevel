package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@WebFilter("/*")
public class ScanAllMoving implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        String ip=request.getRemoteAddr();
        StringBuffer url=request.getRequestURL();
        String user_id="not_register";

        Cookie [] cookies = request.getCookies();
        for(Cookie c:cookies){
            if(c.getName().equals("user_id")){
                user_id=c.getValue();
            }
        }

        Date date = new Date(System.currentTimeMillis());

        System.out.println("Date :"+date+" | User_id: "+user_id+" | ip: "+ip+" | URL: "+url);

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
