package com.natanista.springreactivemongodb.controller;

import com.natanista.springreactivemongodb.dto.ProductDTO;
import com.natanista.springreactivemongodb.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    @GetMapping
    public Flux<ProductDTO> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDTO> getProduct(@PathVariable("id") String id){
        return productService.getProduct(id);
    }

    @GetMapping
    public Flux<ProductDTO> getProductsBetweenRange(
            @RequestParam("min") BigDecimal min,
            @RequestParam("max") BigDecimal max){
            return productService.getProductInRange(min,max);
    }

    @PostMapping
    public Mono<ProductDTO> saveProduct(@RequestBody Mono<ProductDTO> productDTO){
        return productService.saveProduct(productDTO);
    }

    @PutMapping("/{id}")
    public Mono<ProductDTO> updateProduct(
            @RequestBody Mono<ProductDTO> productDTO,
            @PathVariable("id") String id
    ){
        return productService.updateProduct(productDTO, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") String id){
        return productService.deleteProduct(id);
    }


}
