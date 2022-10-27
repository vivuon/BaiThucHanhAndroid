package com.example.truongvivuon;

public class Order {
    String id;
    String date;
    Double pricetotal;
    int state;
    Restaurant restaurant;

    public Order() {
    }

    public Order(String id, String date, Double pricetotal, int state, Restaurant restaurant) {
        this.id = id;
        this.date = date;
        this.pricetotal = pricetotal;
        this.state = state;
        this.restaurant = restaurant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPricetotal() {
        return pricetotal;
    }

    public void setPricetotal(Double pricetotal) {
        this.pricetotal = pricetotal;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
