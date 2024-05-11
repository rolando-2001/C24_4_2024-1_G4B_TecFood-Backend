package com.backend.app.controllers;



import com.backend.app.persistence.entities.CategoryEntity;
import com.backend.app.persistence.entities.ProductEntity;
import com.backend.app.services.CategoryServiceImpl;
import com.backend.app.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/product/")
public class ProductController {


    @Autowired
     private ProductServiceImpl productServiceimpl;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @GetMapping("products")
    public List<ProductEntity> fiadAll(){
        return productServiceimpl.findAllProducts();
    }

    @GetMapping("categorys")
    public List<CategoryEntity> fiadAllCategory(){
        return categoryServiceImpl.fiadAllCategorys();
    }


}
