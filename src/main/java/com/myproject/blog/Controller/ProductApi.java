package com.myproject.blog.Controller;

import java.net.URI;
import java.util.List;
 
import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myproject.blog.Entity.Product;
import com.myproject.blog.Repo.ProductRepository;
 
@RestController
@RequestMapping("/products")
public class ProductApi {
 
    @Autowired private ProductRepository repo;
     
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
        Product savedProduct = repo.save(product);
        URI productURI = URI.create("/products/" + savedProduct.getId());
        return ResponseEntity.created(productURI).body(savedProduct);
    }
     
    @GetMapping
    public List<Product> list() {
        return repo.findAll();
    }
}