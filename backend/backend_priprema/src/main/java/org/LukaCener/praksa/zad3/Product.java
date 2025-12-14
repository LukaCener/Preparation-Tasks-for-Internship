package org.LukaCener.praksa.zad3;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Product extends PanacheEntity {

    @NotBlank
    public String name;

    @Positive
    public double price;

    @NotBlank
    public String category;
}