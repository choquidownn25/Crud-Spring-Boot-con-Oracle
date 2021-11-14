package com.example.demo.service;

import com.example.demo.models.Products;

import java.util.List;
//Servicios para el crud
public interface IProductsService {
    List<Products> getAllProducts();
    Products saveProducts(Products products);
    Products getProductsById(long id);
    Products deleteProductsById(long id);
}
