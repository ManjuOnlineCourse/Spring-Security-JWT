package com.myproject.blog.Entity;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
 
import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    @Column(nullable = false, length = 128)
    @NotNull @Length(min = 5, max = 128)
    private String name;
     
    private float price;
 
    // getters and setters are not shown for brevity
}