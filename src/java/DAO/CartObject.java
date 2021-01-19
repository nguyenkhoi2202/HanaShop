/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CartDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nguyen Khoi
 */
public class CartObject  implements Serializable{

    private Map<String, CartDTO> item = null;

    public Map<String, CartDTO> getItem() {
        return item;
    }

    public void add(CartDTO dto) {
        if (this.item == null) {
            this.item = new HashMap<>();
        }
        if (this.item.containsKey(dto.getFoodName())) {
            int quantity = this.item.get(dto.getFoodName()).getQuantity();
            dto.setQuantity(quantity + 1);
        }
        this.item.put(dto.getFoodName(), dto);
    }
    public  int getQuantity(String name){
        if (this.item == null) {
            return 0;
        }
        return this.item.get(name).getQuantity();
    }

    public void delete(String foodName) {
        if (this.item == null) {
            return;
        }
        if (this.item.containsKey(foodName)) {
            this.item.remove(foodName);
        }
        if (this.item.isEmpty()) {
            this.item = null;
        }
    }
}
