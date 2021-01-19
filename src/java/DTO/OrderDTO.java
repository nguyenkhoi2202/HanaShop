/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author Nguyen Khoi
 */
public class OrderDTO implements Serializable {

    private String orderid;
    private String username;
    private Float totalprice;
    private String createdate;

    public OrderDTO() {
    }

    public OrderDTO(String username, Float totalprice, String createdate) {
        this.username = username;
        this.totalprice = totalprice;
        this.createdate = createdate;
    }

    public OrderDTO(String orderid, String username, Float totalprice, String createdate) {
        this.orderid = orderid;
        this.username = username;
        this.totalprice = totalprice;
        this.createdate = createdate;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalprice) {
        this.totalprice = totalprice;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }


}
