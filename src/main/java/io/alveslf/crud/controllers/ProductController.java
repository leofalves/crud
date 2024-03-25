package io.alveslf.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.alveslf.crud.domain.product.Product;
import io.alveslf.crud.domain.product.ProductRepository;
import io.alveslf.crud.domain.product.RequestProduct;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	
	@GetMapping
	public ResponseEntity getAllProducts(){
		var allProducts = repository.findAll();
		return ResponseEntity.ok(allProducts);
	}
	
	@PostMapping
	public ResponseEntity creatProduct(@RequestBody @Valid RequestProduct data) {
		Product newProduct = new Product(data);
		repository.save(newProduct);	
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity updateProducct(@RequestBody @Valid RequestProduct data) {
		System.out.println(data);
		Product product = repository.getReferenceById(data.id());
		product.setName(data.name());
		product.setPrice(data.price());
		repository.save(product);
		return ResponseEntity.ok(data);
		//return ResponseEntity.ok().build();
	}

}
