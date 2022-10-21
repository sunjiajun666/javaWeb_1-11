package com.buba.dao;

import com.buba.entity.Book;
import com.buba.entity.User;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-13
 * Time:10:33
 */
public interface UserDao {
    // 注册用户,添加用户
    int login(User user);

    // 查询,通过用户名和密码在数据库查询是否存在该用户
    int logout(String userName, String userPassword);

//查询t_book信息
    List<Book> fillBook(Integer booId);
//    查询t_book有多少条信息
    int fillbookcont();
}
