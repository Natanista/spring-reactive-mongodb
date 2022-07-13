package com.natanista.springreactivemongodb.repository;

import com.natanista.springreactivemongodb.dto.ProductDTO;
import com.natanista.springreactivemongodb.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<ProductDTO> findByPriceBetween(Range<BigDecimal> priceRange);
}
