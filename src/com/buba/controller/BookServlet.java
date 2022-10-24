package com.buba.controller;

import com.buba.dao.BookDao;
import com.buba.dao.Impl.BookDaoImpl;
import com.buba.dao.Impl.UserDaoImpl;
import com.buba.dao.UserDao;
import com.buba.entity.Book;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookServlet extends ViewBaseServlet{


    private BookDao bookDao=new BookDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
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

        }
        if(req.getParameter("method").equals("addbook")){

            this.addbook(req,resp);
        }
        if(req.getParameter("method").equals("amend")){
//            processTemplate("/pages/manager/book_amend",req,resp);
            this.amend(req,resp);
        }    if(req.getParameter("method").equals("updateamend")){
//            processTemplate("/pages/manager/book_amend",req,resp);
            this.updateamend(req,resp);
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
        System.out.println("提交");
        System.out.println("方法开始执行");
        resp.setContentType("application/json;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        // 验证请求是否满足要求（post 请求 / enctype 是否以multipart打头
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        // 如果不满足要求就立即结束对该请求的处理
        if (!isMultipart) {
            return;
        }
        try {
            // FileItem 是表单中的每一个元素的封装
            // 创建一个 FileItem 的工厂类
            FileItemFactory factory = new DiskFileItemFactory();
            // 创建一个文件上传处理器（装饰设计模式）
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析请求
            List<FileItem> items = upload.parseRequest(req);
            System.out.println(items);


            FileItem fileItem = items.get(0); //获取到的图片
            // 文件类型
            // 获取文件后缀名
            String imgtype = fileItem.getName().substring(fileItem.getName().lastIndexOf("."));
            // 给文件重新命名防止重复
            String imgName = UUID.randomUUID() + imgtype;
            System.out.println();
            //将要保存的文件夹
            String path="E:\\Git-Space\\JavaWeb_1-11\\web\\static\\uploads";
            // 将上传的文件保存到服务器
            fileItem.write(new File(path, imgName));

            // 把服务器中的路径添加到数据库中
            String sqlPath=null;
            sqlPath = "static/uploads/" + imgName;

            Book book = new Book();
            book.setImgPath(sqlPath); //sqlPath是 static/uploads/路径 加图片名字添加到了数据库

            //普通数据
            book.setName(items.get(1).getString("UTF-8"));
            book.setPrice(Double.valueOf(items.get(2).getString("UTF-8")));
            book.setAuthor(items.get(3).getString("UTF-8"));
            book.setSales(Integer.valueOf(items.get(4).getString("UTF-8")));
            book.setStock(Integer.valueOf(items.get(5).getString("UTF-8")));
            System.out.println("访问路径：" + sqlPath);
         bookDao.addbook(book);
            System.out.println("添加的数据"+book);
            this.fillBookstage(req,resp);
            HttpSession session = req.getSession();
            Object pageCount = session.getAttribute("contmax");

//            resp.sendRedirect("/JavaWeb/Admin?method=book_manager&pageNo="+pageCount);//跳到最后一页
            resp.sendRedirect("bookHtml?method=book_manager&cupage="+pageCount);//跳到最后一页
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   protected void amend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String id = req.getParameter("ids");
       String img = req.getParameter("img");
       System.out.println(img);
       String name = req.getParameter("Name");
       String author = req.getParameter("author");
       String price = req.getParameter("price");
        String sales = req.getParameter("sales");
        String stock = req.getParameter("stock");
       Book book = new Book(name,Double.valueOf(price),author,Integer.valueOf(sales),Integer.valueOf(stock),img);
       book.setBookId(Integer.valueOf(id));
       System.out.println("修改的数据"+book);
       req.setAttribute("book",book);

       processTemplate("/pages/manager/book_amend",req,resp);
    }
    protected void updateamend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
       String img = req.getParameter("img");
       System.out.println(img);
       String name = req.getParameter("Name");
       String author = req.getParameter("author");
       String price = req.getParameter("price");
        String sales = req.getParameter("sales");
        String stock = req.getParameter("stock");

       Book book = new Book(name,Double.valueOf(price),author,Integer.valueOf(sales),Integer.valueOf(stock),img);
   book.setBookId(Integer.valueOf(id));

       bookDao.amend(book);
       System.out.println("11111"+book);
       req.setAttribute("book",book);

        this.fillBookstage(req,resp);
    }

}
