package com.example.model.service.category;

import com.example.model.entity.Categories;

import java.util.List;

public interface CategoryService {
    List<Categories> findAll();

    boolean create(Categories category);

    Categories findById(int id);

    boolean update(Categories category);

    void delete(int id);

    boolean isCategoryNameExists(String nameCategory);

}
