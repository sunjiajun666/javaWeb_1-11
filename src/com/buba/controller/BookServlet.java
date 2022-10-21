package com.buba.controller;

import com.buba.dao.BookDao;
import com.buba.dao.Impl.BookDaoImpl;
import com.buba.dao.Impl.UserDaoImpl;
import com.buba.dao.UserDao;
import com.buba.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookServlet extends ViewBaseServlet{


    private BookDao bookDao=new BookDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 图书管理
        if (req.getParameter("method").equals("book_manager")){
            this.fillBookstage(req,resp);
        }
        if(req.getParameter("method").equals("delbook")){
            this.delbook(req,resp);
        }
        // 添加图书
        if(req.getParameter("method").equals("book_edit")){
            processTemplate("/pages/manager/book_edit",req,resp);

        }   if(req.getParameter("method").equals("addbook")){

            this.addbook(req,resp);
        }

    }

//查询t_book数据库所有数据渲染到book_manage （后台管理图书）页面
    protected void fillBookstage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer cupage = 1;

        String cupage1 = req.getParameter("cupage");
        if (cupage1!=null){
            cupage = Integer.parseInt(cupage1);
        }
        HttpSession session = req.getSession();
//        把这个值设置到会话域里
        session.setAttribute("cupage",cupage);
        List<Book> booksmanage = bookDao.fillBookstage(cupage);
        System.out.println("全部数据"+booksmanage);
        req.setAttribute("booksmanage",booksmanage);

        int fillbookcontstage = bookDao.fillbookcontstage();

        System.out.println(fillbookcontstage);
        req.getSession().setAttribute("fillbookcontstage",fillbookcontstage);
        int contmax=(fillbookcontstage+5-1)/5;
        ArrayList<Integer> in = new ArrayList<>();
        for (int i = 1; i <=contmax; i++) {
            in.add(i);
        }
        System.out.println("拿到的所有页数的一个数组"+in);
        req.setAttribute("in",in);
        System.out.println(contmax);
        req.getSession().setAttribute("contmax",contmax);
        processTemplate("/pages/manager/book_manager",req,resp);
    }
    protected void delbook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        System.out.println(id);
         bookDao.delbook(Integer.valueOf(id));
        this.fillBookstage(req,resp);


    }
    protected void addbook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String img = req.getParameter("img");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String author = req.getParameter("author");
        String sales = req.getParameter("sales");
        String stock = req.getParameter("stock");
        Book book = new Book(img,Double.valueOf(name),price,Integer.valueOf(author),Integer.valueOf(sales),stock);
        System.out.println("添加的数据"+book);
        bookDao.addbook(book);
        this.fillBookstage(req,resp);
    }

}
