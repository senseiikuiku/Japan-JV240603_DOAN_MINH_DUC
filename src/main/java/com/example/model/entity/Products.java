package com.example.model.entity;

import jdk.jfr.Category;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 100,unique = true,nullable = false)
    private int id;
    @Column(name = "nameProduct",length = 100,nullable = false,unique = true)
    private String nameProduct;
    @Column(name = "price",nullable = false)
    private double price;
    @Column(name = "image")
    private String image;
    @Column(name = "status")
    @ColumnDefault("1")
    private boolean status;
    @ManyToOne
    @JoinColumn(name="category_id",referencedColumnName = "id")
    private Categories category;

    public Products() {
    }

    public Products(int id, String nameProduct, double price, String image, boolean status, Categories category) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.image = image;
        this.status = status;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
