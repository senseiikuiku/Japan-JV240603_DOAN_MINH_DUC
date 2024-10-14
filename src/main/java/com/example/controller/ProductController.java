package com.example.controller;

import com.example.model.entity.Categories;
import com.example.model.entity.Products;
import com.example.model.service.category.CategoryService;
import com.example.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        List<Products> products = productService.findAll();
        model.addAttribute("products", products);
        System.out.println(products);
        return "products/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Products product = new Products();
        model.addAttribute("product", product);
        List<Categories> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "products/add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute Products product, @RequestParam("imgFile") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String path = "E:\\academy\\MD3\\Exam_JavaCore_Webapp\\src\\main\\webapp\\uploads";
        File destination = new File(path + "/" + fileName);
        try {
            Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        product.setImage(fileName);
        if (productService.create(product)) {
            return "redirect:/products";
        }
        return "redirect:/products/add";
    }

    @GetMapping("/back")
    public String back(Model model) {
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Products product = productService.findById(id);
        model.addAttribute("product", product);
        List<Categories> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "products/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @ModelAttribute Products product, @RequestParam("imgFile") MultipartFile file, @RequestParam("currentImage") String currentImage) {
        if (file.isEmpty()) {
            product.setImage(currentImage);
        } else {
            String fileName = file.getOriginalFilename();
            String path = "E:\\academy\\MD3\\Exam_JavaCore_Webapp\\src\\main\\webapp\\uploads";
            File destination = new File(path + "/" + fileName);
            try {
                Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            product.setImage(fileName);
        }
        if (productService.update(product)) {
            return "redirect:/products";
        }
        return "redirect:/products/edit/{id}";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
