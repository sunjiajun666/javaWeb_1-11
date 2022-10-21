package com.buba.entity;

import java.util.Date;

public class Order {
    private  Integer orderId;
    private  Integer orderCount;
    private  Double  orderAmount;
    private  Integer userId;
    private  Integer orderStatus;
    private  Date    createTime;
    private  Date    updateTime;
    private  String  comment;

    public Order() {
    }

    public Order(Integer orderId, Integer orderCount, Double orderAmount, Integer userId, Integer orderStatus, Date createTime, Date updateTime, String comment) {
        this.orderId = orderId;
        this.orderCount = orderCount;
        this.orderAmount = orderAmount;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderCount=" + orderCount +
                ", orderAmount=" + orderAmount +
                ", userId=" + userId +
                ", orderStatus=" + orderStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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
