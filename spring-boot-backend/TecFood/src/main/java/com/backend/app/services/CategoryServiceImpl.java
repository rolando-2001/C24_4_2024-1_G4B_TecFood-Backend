package com.backend.app.services;

import com.backend.app.models.ICategory;
import com.backend.app.persistence.entities.CategoryEntity;
import com.backend.app.persistence.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategory {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryEntity> fiadAllCategorys() {
        return categoryRepository.findAll();
    }
}
