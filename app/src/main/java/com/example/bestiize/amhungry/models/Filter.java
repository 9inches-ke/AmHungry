package com.example.bestiize.amhungry.models;

/**
 * Created by Bestiize on 15/11/2558.
 */
public class Filter {
    private String price;
    private String res_type;
    private String user_x;
    private String user_y;
    private String distance;

    public String getRes_type() {
        return res_type;
    }

    public void setRes_type(String res_type) {
        this.res_type = res_type;
    }

    public String getUser_x() {
        return user_x;
    }

    public void setUser_x(String user_x) {
        this.user_x = user_x;
    }

    public String getUser_y() {
        return user_y;
    }

    public void setUser_y(String user_y) {
        this.user_y = user_y;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
