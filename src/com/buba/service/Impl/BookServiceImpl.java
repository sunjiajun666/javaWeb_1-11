package com.buba.service.Impl;

import com.buba.entity.Book;
import com.buba.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookService bookService=new BookServiceImpl();
    @Override
    public List<Book> fillBookstage(Integer booId) {

        return bookService.fillBookstage(booId);
    }

    @Override
    public List<Book> fillBookallstage() {
        return bookService.fillBookallstage();
    }

    @Override
    public int delbook(Integer book) {
        return bookService.delbook(book);
    }

    @Override
    public int addbook(Book book) {
        return bookService.addbook(book);
    }


}
