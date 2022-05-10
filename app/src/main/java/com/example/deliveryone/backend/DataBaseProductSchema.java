package com.example.deliveryone.backend;

public class DataBaseProductSchema {
    private Integer bar_code;
    private String description;
    private String taxType;
    private Integer cost;

    public DataBaseProductSchema(Integer bar_code, String description, String taxType, Integer cost) {
        this.bar_code = bar_code;
        this.description = description;
        this.taxType = taxType;
        this.cost = cost;
    }

    public Integer getBar_code() {
        return bar_code;
    }

    public void setBar_code(Integer bar_code) {
        this.bar_code = bar_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
