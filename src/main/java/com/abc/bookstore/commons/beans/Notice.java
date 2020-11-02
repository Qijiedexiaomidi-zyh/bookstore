package com.abc.bookstore.commons.beans;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Notice {

    private Integer n_id;
    private String title;
    private String details;
    private Timestamp n_time;
}
