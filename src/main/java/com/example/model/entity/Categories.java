package com.example.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 100, unique = true, nullable = false)
    private int id;
    @NotBlank(message = "Tên danh mục không được để trống")
    @Column(name = "nameCategory", length = 100, unique = true, nullable = false)
    private String nameCategory;
    @NotBlank(message = "Description không được để trống")
    @Column(name = "description", length = 200, nullable = false)
    private String description;
    @OneToMany(mappedBy = "category")
    private Set<Products> products;

    public Categories() {
    }

    public Categories(int id, String nameCategory, String description, Set<Products> products) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.description = description;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Products> getProducts() {
        return products;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }
}
