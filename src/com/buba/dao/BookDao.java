package com.buba.dao;


import com.buba.entity.Book;

import java.util.List;

public interface BookDao {


    List<Book> fillBookstage(Integer cupage);
    int fillbookcontstage();

    int delbook(Integer book);
int addbook(Book book);

int amend(Book book);
}
