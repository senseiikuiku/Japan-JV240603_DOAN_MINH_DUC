package com.example.model.service.category;

import com.example.model.dao.category.CategoryDAO;
import com.example.model.entity.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;


    @Override
    public List<Categories> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public boolean create(Categories category) {
        return categoryDAO.create(category);
    }

    @Override
    public Categories findById(int id) {
        return categoryDAO.findById(id);
    }

    @Override
    public boolean update(Categories category) {
        return categoryDAO.update(category);
    }

    @Override
    public void delete(int id) {
        categoryDAO.delete(id);
    }

    @Override
    public boolean isCategoryNameExists(String nameCategory) {
        return categoryDAO.isCategoryNameExists(nameCategory);
    }
}
