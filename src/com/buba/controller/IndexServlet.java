package com.buba.controller;

import com.buba.dao.BookDao;
import com.buba.dao.Impl.BookDaoImpl;
import com.buba.dao.Impl.UserDaoImpl;
import com.buba.dao.UserDao;
import com.buba.entity.Book;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-09-30
 * Time:08:18
 */
public class IndexServlet extends ViewBaseServlet {
    private BookDao bookDao=new BookDaoImpl();
    private UserDao userDao=new UserDaoImpl();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            this.fillbookbwetten(req,resp);
        processTemplate("index",req,resp);
//        this.bookAll(req,resp);
    }


    protected void fillbookbwetten(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String minprice = req.getParameter("minPrice");
        String maxprice = req.getParameter("maxPrice");
        HttpSession session = req.getSession();
        session.setAttribute("minprice",minprice);
        session.setAttribute("maxprice",maxprice);

        Double maxprice1 = userDao.maxprice();
        if (minprice!=null || maxprice!=null){
            System.out.println(111);
            this.bookpriceAll(Double.valueOf(minprice),Double.valueOf(maxprice),req,resp);
        }else {

            this.bookpriceAll(0.0,maxprice1, req,resp);

        }

    }




    protected void bookpriceAll(Double min , Double max, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 设置一个当前页
        Integer booId = 1;

//        拿到这个值
        String booId1 = req.getParameter("booId");
//        如果这个值不等于null 那这个值就是当前页
        if(booId1 != null){
            booId = Integer.parseInt(booId1);
        }
//        设置一个会话域
        HttpSession session = req.getSession();
//        把这个值设置到会话域里
        session.setAttribute("booId",booId);
        List<Book> booksa = userDao.fillbookbwetten(min,max,booId);
        System.out.println( "分页的数据"+booksa);
//        查询全部数据的个数
        int fillbookcont = userDao.bookmoney(min,max);
        System.out.println("总页数"+fillbookcont);
//        计算总页数的公式 (总页数+10-1)/10
        int cont=(fillbookcont+10-1)/10;
//        创建数组存放页码循环添加到index页面
        ArrayList<Integer> in = new ArrayList<>();
        for (int i = 1; i <=cont; i++) {
            in.add(i);
        }
        System.out.println("拿到的所有页数的一个数组"+in);
        req.setAttribute("in",in);
//        会话域设置最大页数
        req.getSession().setAttribute("cont",cont);
        System.out.println("总页数"+cont);
//       设置
        req.setAttribute("fillbookcont",fillbookcont);
        req.setAttribute("booksa",booksa);




    }


}
