package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Esto le dice a Hibernate que haga una tabla de esta clase
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products") //Nombre de la tabla CUSTOMER
public class Products {
    @Id // Id llave primaria
    @Column(name = "product_id")
    Long id;
    @Column(name = "product_name")
    String name;
    @Column(name = "brand_id")
    int brand_id;
    @Column(name = "category_id")
    int category_id;
    //@Temporal(TemporalType.DATE) CREATED_DATE
    @Column(name = "model_year")
    int model_year;
    @Column(name = "list_price")
    int list_price;
}
