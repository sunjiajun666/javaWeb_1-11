package com.buba.entity;

import java.util.Date;

public class Book {
    private Integer bookId;
    private String  Name;
    private Double  price;
    private String  author;
    private Integer sales;
    private Integer stock;
    private String  imgPath;
    private Date    createTime;
    private Date    updateTime;
    private String  comment;

    public Book(Integer bookId, String Name, Double price, String author, Integer sales, Integer stock, String imgPath, Date createTime, Date updateTime, String comment) {
        this.bookId = bookId;
        this.Name = Name;
        this.price = price;
        this.author = author;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.comment = comment;
    }

    public Book(String Name, Double price, String author, Integer sales, Integer stock, String imgPath) {
        this.imgPath = imgPath;
        this.Name = Name;
        this.price = price;
        this.author = author;
        this.sales = sales;
        this.stock = stock;

    }

    public Book(String name, Double price, String author, Integer sales, Integer stock) {
        Name = name;
        this.price = price;
        this.author = author;
        this.sales = sales;
        this.stock = stock;
    }

    public Book(Double price, Integer sales, Integer stock) {
        this.price = price;
        this.sales = sales;
        this.stock = stock;
    }

    public Book() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", Name='" + Name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
