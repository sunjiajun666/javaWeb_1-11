package com.buba.service;

import com.buba.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> fillBookstage(Integer booId);
    List<Book> fillBookallstage();
    int delbook(Integer book);
    int addbook(Book book);
}
