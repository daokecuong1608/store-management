package com.sapo.store_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRes {

    private int id;

    private int quantity;

    private String code;

    private String name;

    private String description;

    private int price;

    private int capital_price;

    private String image;

    // private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCapital_price() {
        return capital_price;
    }

    public void setCapital_price(int capital_price) {
        this.capital_price = capital_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // public String getStatus() {
    // return status;
    // }
    //
    // public void setStatus(String status) {
    // this.status = status;
    // }
}
