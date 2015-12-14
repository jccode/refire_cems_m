package com.hongdingltd.hello.validation;

import com.hongdingltd.hello.validation.annotation.Order;

import java.util.Date;

/**
 * Created by jcchen on 15-12-1.
 */
@Order(previous = "from", next = "to")
public class OrderQuery {

    private Date from;

    private Date to;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
