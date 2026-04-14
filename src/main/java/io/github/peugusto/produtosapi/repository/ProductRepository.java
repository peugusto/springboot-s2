package io.github.peugusto.produtosapi.repository;

import io.github.peugusto.produtosapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
