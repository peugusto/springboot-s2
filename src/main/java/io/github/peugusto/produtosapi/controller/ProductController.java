package io.github.peugusto.produtosapi.controller;


import io.github.peugusto.produtosapi.model.Product;
import io.github.peugusto.produtosapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product save(@RequestBody Product obj){
        String id = UUID.randomUUID().toString();
        obj.setId(id);
        productRepository.save(obj);
        return obj;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id){

        if (!productRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.ok("product deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") String id){
     return productRepository.findById(id)
             .map(product -> ResponseEntity.ok(product))
             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateById(@PathVariable("id") String id,
                                     @RequestBody Product obj){
        if(!productRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

        obj.setId(id);
        productRepository.save(obj);

        return ResponseEntity.ok(obj);
    }
}
