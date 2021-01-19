/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Nguyen Khoi
 */
public class CartDTO {
    private String cusName;
    private String id;
    private String foodName;
    private String description;
    private int quantity;
    private float price;

    public CartDTO() {
    }

    public CartDTO(String cusName, String id, String foodName, String description, int quantity, float price) {
        this.cusName = cusName;
        this.id = id;
        this.foodName = foodName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    

   
}
