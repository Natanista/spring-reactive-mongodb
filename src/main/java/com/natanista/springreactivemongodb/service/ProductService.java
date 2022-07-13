package com.natanista.springreactivemongodb.service;

import com.natanista.springreactivemongodb.dto.ProductDTO;
import com.natanista.springreactivemongodb.entity.Product;
import com.natanista.springreactivemongodb.repository.ProductRepository;
import com.natanista.springreactivemongodb.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public Flux<ProductDTO> getProducts() {
        return productRepository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ProductDTO> getProduct(String id) {
        return productRepository.findById(id).map(AppUtils::entityToDto);
    }

    public Flux<ProductDTO> getProductInRange(BigDecimal min, BigDecimal max) {
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }

    public Mono<ProductDTO> saveProduct(Mono<ProductDTO> productDTOMono) {
        return productDTOMono
                .map(AppUtils::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDTO> updateProduct(Mono<ProductDTO> productDTOMono, String id) {
        return productRepository.findById(id)
                .flatMap(p -> productDTOMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }


}
