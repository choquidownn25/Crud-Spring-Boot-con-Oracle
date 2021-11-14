/*
JpaRepository es una extensión específica de JPA del repositorio.
Contiene la API completa de CrudRepository y PagingAndSortingRepository.
Por lo tanto, contiene API para operaciones CRUD básicas y también API para paginación y clasificación.
 */

package com.example.demo.repository;

import com.example.demo.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductsRepository extends JpaRepository<Products, Long > {
}
