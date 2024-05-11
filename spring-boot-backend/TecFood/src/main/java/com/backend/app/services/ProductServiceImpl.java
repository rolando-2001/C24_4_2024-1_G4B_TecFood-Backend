package com.backend.app.services;

import com.backend.app.models.IproductService;
import com.backend.app.persistence.entities.ProductEntity;
import com.backend.app.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements IproductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }

}
