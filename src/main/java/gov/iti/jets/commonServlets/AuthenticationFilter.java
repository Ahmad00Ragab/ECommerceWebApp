//package gov.iti.jets.commonServlets;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter("/user/*")
//public class AuthenticationFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//
//        if (req.getSession().getAttribute("loggedInUser") == null) {
//            resp.sendRedirect("/login.jsp");
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//}