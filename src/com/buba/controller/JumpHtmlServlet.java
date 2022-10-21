package com.buba.controller;

import com.buba.dao.Impl.UserDaoImpl;
import com.buba.dao.UserDao;
import com.buba.entity.Book;
import com.buba.entity.User;
import com.mysql.cj.Session;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-12
 * Time:11:30
 */
@WebServlet(urlPatterns = "/index.do")
public class JumpHtmlServlet extends ViewBaseServlet {
    private UserDao userDao=new UserDaoImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 请求转发跳转到 /WEB-INF/view/ 路径.html

        // 判断跳转路径
        // 登录
        if(req.getParameter("jump").equals("login")){
//
            processTemplate("/pages/user/login",req,resp);
        }
        // 登录成功
        if(req.getParameter("jump").equals("login_success")){
            this.regist(req,resp);

        }
        // 注册
        if(req.getParameter("jump").equals("regist")){
            processTemplate("/pages/user/regist",req,resp);


        }
        // 注册成功
        if(req.getParameter("jump").equals("regist_success")){
            this.login(req,resp);

        }
        // 购物车
        if(req.getParameter("jump").equals("cart")){
            processTemplate("/pages/cart/cart",req,resp);
        }

        // 图书订单管理
        if(req.getParameter("jump").equals("order_manager")){
            processTemplate("/pages/manager/order_manager",req,resp);
        }

        // 我的订单
        if(req.getParameter("jump").equals("order")){
            processTemplate("/pages/order/order",req,resp);
        }
//        注销
        if(req.getParameter("jump").equals("logout")){
            this.logout(req,resp);

        } if (req.getParameter("method").equals("fillBook")){
            this.fillBook(req,resp);
        }
    }

//注册
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String em = req.getParameter("em");
        System.out.println(id);
        System.out.println(password);
        System.out.println(em);
        User user = new User(id,password,em);
        System.out.println(user);
        userDao.login(user);
        req.getSession().setAttribute("id",id);
        processTemplate("/pages/user/regist_success",req,resp);
    }
//登录
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(id);
        System.out.println(password);

        String hashedPwd1 = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = new User(id,password);
        System.out.println("用户名和密码"+user);
        int userByNameAndPassword = userDao.logout(id,hashedPwd1);
        System.out.println(userByNameAndPassword);
        if (userByNameAndPassword==1){

            req.getSession().setAttribute("id",id);
            processTemplate("/pages/user/login_success",req,resp);
        }else {
            processTemplate("/pages/user/login",req,resp);
            System.out.println("密码错误");
        }

//        processTemplate("/pages/user/regist_success",req,resp);
    }

//注销
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("username");
        HttpSession session = req.getSession();
        System.out.println(session);
        if (id==null){
            session.setAttribute("id",id);
//            processTemplate("index",req,resp);
            req.getRequestDispatcher("index").forward(req,resp);
        }else {
            processTemplate("index",req,resp);
        }

    }


    //查询t_book数据库所有数据渲染到index
    protected void fillBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer booId = 1;
        List<Book> books = userDao.fillBook(booId);
        HttpSession session = req.getSession();
        req.setAttribute("books",books);
        processTemplate("index",req,resp);


    }

}
