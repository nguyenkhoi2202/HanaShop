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
public class OrderDetailDTO implements Serializable{
    private int orderid;
    private String name;
    private String id;
    private float  price;
    private int quantity;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderid, String name, String id, float price, int quantity) {
        this.orderid = orderid;
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" + "orderid=" + orderid + ", name=" + name + ", id=" + id + ", price=" + price + ", quantity=" + quantity + '}';
    }

   

}
