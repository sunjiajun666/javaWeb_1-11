//package com.buba.Filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = "/*")
//public class MyFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req=(HttpServletRequest)servletRequest;
//        HttpServletResponse resp=(HttpServletResponse) servletResponse;
//
//        String requestURI = req.getRequestURI();
//
//        if (requestURI.contains("index") || requestURI.contains("static") ){
////            直接放行
//            filterChain.doFilter(req,resp);
//            return;
//        }
//        if(req.getParameter("jump").equals("login")){
//       filterChain.doFilter(req,resp);
//       return;
//        }
//        // 登录成功
//        if(req.getParameter("jump").equals("login_success")){
//            filterChain.doFilter(req,resp);
//            return;
//
//        }
//        if(req.getParameter("jump").equals("regist_success")){
//            filterChain.doFilter(req,resp);
//            return;
//        }
//
//        if(req.getParameter("jump").equals("regist")){
//
//            filterChain.doFilter(req,resp);
//            return;
//        }
//        HttpSession session = req.getSession();
//        Object user = session.getAttribute("id");
//        System.out.println(user);
//        if(null != user){// 如果登录过 放行
//            filterChain.doFilter(req,resp);
//        }else{// 没有登录过,跳转至登录页
//
//            resp.sendRedirect("http://localhost:8080/jumpHtml?jump=login");
//
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
