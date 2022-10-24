package com.buba.dao.Impl;

import com.buba.dao.BookDao;
import com.buba.entity.Book;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDaoImpl implements BookDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDateSource());
    @Override
    public List<Book>fillBookstage(Integer cupage) {
        String sql="select * from t_book limit ?,5";
        List<Book> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class),(cupage-1)*5);
        return query;
    }

    @Override
    public int fillbookcontstage() {
        String sql="select count(*) from t_book ";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);

        return integer;
    }

    @Override
    public int delbook(Integer book) {
        String sql="delete from t_book where book_id=?";
        int update = jdbcTemplate.update(sql, book);

        return update;
    }

    @Override
    public int addbook(Book book) {
        String sql="insert into t_book(img_path,name,price,author,sales,stock) values(?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, book.getImgPath(), book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock());
        return update;
    }

    @Override
    public int amend(Book book) {
        String sql="update t_book set img_path=?,Name=?, price=?,author=?,sales=?,stock=? where book_id=?";
        int update = jdbcTemplate.update(sql,book.getImgPath(),book.getName(), book.getPrice(),book.getAuthor(), book.getSales(), book.getStock(), book.getBookId());
        return update;
    }


}
