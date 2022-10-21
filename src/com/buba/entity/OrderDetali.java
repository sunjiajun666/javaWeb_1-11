package com.buba.entity;

import java.util.Date;

public class OrderDetali {
    private Integer orderDetailId;
    private Integer bookId;
    private String  bookName;
    private Integer bookCount;
    private Double  amount;
    private Integer orderId;
    private Date    createTime;
    private Date    updateTime;
    private String  comment;

    public OrderDetali() {
    }

    public OrderDetali(Integer orderDetailId, Integer bookId, String bookName, Integer bookCount, Double amount, Integer orderId, Date createTime, Date updateTime, String comment) {
        this.orderDetailId = orderDetailId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookCount = bookCount;
        this.amount = amount;
        this.orderId = orderId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "OrderDetali{" +
                "orderDetailId=" + orderDetailId +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookCount=" + bookCount +
                ", amount=" + amount +
                ", orderId=" + orderId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
}
