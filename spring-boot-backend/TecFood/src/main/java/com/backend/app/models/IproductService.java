package com.backend.app.models;

import com.backend.app.persistence.entities.ProductEntity;

import java.util.List;

public interface IproductService {
    List<ProductEntity> findAllProducts();
}
