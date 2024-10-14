package com.example.model.service.product;

import com.example.model.dao.product.ProductDAO;
import com.example.model.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Products> findAll() {
        return productDAO.findAll();
    }

    @Override
    public boolean create(Products product) {
        return productDAO.create(product);
    }

    @Override
    public Products findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public boolean update(Products product) {
        return productDAO.update(product);
    }

    @Override
    public void delete(int id) {
        productDAO.delete(id);
    }
}
