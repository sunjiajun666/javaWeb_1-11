package com.buba.dao.Impl;

import com.alibaba.druid.util.JdbcUtils;
import com.buba.controller.JumpHtmlServlet;
import com.buba.dao.UserDao;
import com.buba.entity.Book;
import com.buba.entity.User;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-10-13
 * Time:10:39
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());

    @Override
    public int login(User user) {

        String sql="insert into t_user(user_name,user_password,email)   values(?,?,?) ";
        int update = jdbcTemplate.update(sql,user.getUserName(), user.getUserPassword(), user.getEmail());
        return update;
    }


    @Override
    public int logout(String userName, String userPassword) {
        String sql="select count(*) from t_user where user_name=? and user_password=?";
        int update = jdbcTemplate.queryForObject(sql,Integer.class,userName,userPassword);
        return update;
    }


    @Override
    public List<Book> fillBook(Integer booId) {
        String sql="select * from t_book limit ?,10";
        List<Book> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class),(booId-1)*10);
        return query;
    }
    @Override
    public int fillbookcont() {
        String sql="select count(*) from t_book ";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        return integer;
    }

    @Override
    public List<Book> fillbookbwetten(Double minm, Double maxm, Integer booId) {
        String sql="select * from t_book where price between ? and ? limit ?,10";
        List<Book> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class),minm,maxm, (booId - 1) * 10);
        return query;
    }

    @Override
    public int bookmoney(Double minm,Double maxm) {
        String sql="select count(*) from t_book where price between ? and ? ";
       int query = jdbcTemplate.queryForObject(sql, Integer.class,minm,maxm);
        return query;
    }

    @Override
    public Double maxprice() {
        String sql="select max(price) from t_book ";
        Double integer = jdbcTemplate.queryForObject(sql,Double.class);
        return integer;

    }


}
