package com.example.model.service.product;

import com.example.model.entity.Products;

import java.util.List;

public interface ProductService {
    List<Products> findAll();

    boolean create(Products product);

    Products findById(int id);

    boolean update(Products product);

    void delete(int id);
}
