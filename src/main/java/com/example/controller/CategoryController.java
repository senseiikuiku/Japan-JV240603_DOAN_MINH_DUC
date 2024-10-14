package com.example.controller;

import com.example.model.entity.Categories;
import com.example.model.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        List<Categories> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "categories/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Categories categories = new Categories();
        model.addAttribute("categories", categories);
        return "categories/add";
    }

    @PostMapping("/add")
    public String create(@Valid @ModelAttribute("categories") Categories categories, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "categories/add";
        }

        if (categoryService.isCategoryNameExists(categories.getNameCategory())) {
            bindingResult.rejectValue("nameCategory", "error.categories", "Tên danh mục đã tồn tại");
            return "categories/add";
        }

        if (categoryService.create(categories)) {
            return "redirect:/categories";
        }
        return "redirect:/categories/add";
    }

    @GetMapping("/back")
    public String back() {
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Categories categories = categoryService.findById(id);
        model.addAttribute("categories", categories);
        return "categories/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, @Valid @ModelAttribute("categories") Categories categories, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "categories/edit";
        }

        if (categoryService.isCategoryNameExists(categories.getNameCategory())) {
            bindingResult.rejectValue("nameCategory", "error.categories", "Tên danh mục đã tồn tại");
            return "categories/edit";
        }
        if (categoryService.update(categories)) {
            return "redirect:/categories";
        }
        return "redirect:/categories/edit/{id}";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }


}
