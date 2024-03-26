package io.alveslf.crud.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.alveslf.crud.domain.product.Product;
import io.alveslf.crud.domain.product.ProductRepository;
import io.alveslf.crud.domain.product.RequestProduct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	@GetMapping
	public ResponseEntity getAllProducts(){
		var allProducts = repository.findAllByActiveTrue();
		return ResponseEntity.ok(allProducts);
	}
	
	@PostMapping
	public ResponseEntity creatProduct(@RequestBody @Valid RequestProduct data) {
		Product newProduct = new Product(data);
		repository.save(newProduct);	
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity updateProducct(@RequestBody @Valid RequestProduct data) {
		//System.out.println(data);
		Optional<Product> optionalProduct = repository.findById(data.id());
		if(optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setName(data.name());
			product.setPrice(data.price());
			repository.save(product);
			return ResponseEntity.ok(product);
		}
		throw new EntityNotFoundException();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deleteProduct(@PathVariable String id) {
		Optional<Product> optionalProduct = repository.findById(id);
		if(optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setActive(false);
			repository.save(product);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
