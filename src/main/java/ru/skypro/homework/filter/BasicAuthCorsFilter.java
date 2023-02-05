package ru.skypro.homework.filter;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.skypro.homework.model.user.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Security;

@Component
public class BasicAuthCorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("Сессия " + session.getAttribute("user"));
        UserAuthentication authentication = new UserAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //servletResponse..addHeader("Access-Control-Allow-Credentials", "true");

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User user = (User) session.getAttribute("user");
        UserAuthentication authentication = new UserAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        filterChain.doFilter(servletRequest, servletResponse);
    }*/
}
