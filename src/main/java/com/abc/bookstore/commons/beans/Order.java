package com.abc.bookstore.commons.beans;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Order {

    private String id;
    private Double money;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private int paystate;
    private Timestamp ordertime;

    private User user;
}
