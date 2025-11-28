package com.android.jsondemo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * @author: laizhiyu
 * @date: 2025/11/25
 * desc:
 */
public class Clothing {
    public String name;
    public double price;
    public String[] tags;
    public Details details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Clothing{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", tags=" + Arrays.toString(tags) +
                ", details=" + details +
                '}';
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public static class Details {
        public String color;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String size;
    }
}