package com.example.demo.service;

import com.example.demo.models.Products;
import com.example.demo.repository.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
@Service
public class ProductsServiceImpl implements IProductsService{
    @Autowired
    private IProductsRepository iProductsRepository;
    //Listar
    @Override
    public List<Products> getAllProducts() {
        try {
            return iProductsRepository.findAll();
        }catch (Exception ex){
            throw new IllegalStateException("Error " + ex.getMessage());
        }
    }
    //Guardar
    @Override
    public Products saveProducts(Products products) {
       if(products != null)
           return iProductsRepository.save(products);
        else
           throw new EntityNotFoundException("Error varificar datos" + products.toString());

    }
    //Buscar por id
    @Override
    public Products getProductsById(long id) {
        //Optional es un objeto contenedor que puede o no contener un valor no nulo.
        // Si el valor es presente, isPresent() devuelve true y get() retorna el valor.
        Optional<Products> optional = iProductsRepository.findById(id);
        Products customer = null;
        if (optional.isPresent()) {
            customer = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return customer;
    }
    //Eliminar
    @Override
    public Products deleteProductsById(long id) {
        Optional<Products> optional = iProductsRepository.findById(id);
        Products products = null;
        if (optional.isPresent()) {
            products = optional.get();
            iProductsRepository.deleteById(id);
            return products;
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
    }
}
