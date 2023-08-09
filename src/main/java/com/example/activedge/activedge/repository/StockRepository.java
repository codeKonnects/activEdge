package com.example.activedge.activedge.repository;

import com.example.activedge.activedge.entity.Stock;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface StockRepository extends R2dbcRepository<Stock, Long> {
    @Query(value = "select * FROM stock WHERE id = :id")
    Mono<Stock> findById(Long id);
}
