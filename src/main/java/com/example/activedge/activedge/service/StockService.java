package com.example.activedge.activedge.service;

import com.example.activedge.activedge.entity.Stock;
import com.example.activedge.activedge.dto.StockDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockService {
    Flux<Stock> getAllStocks();
    Mono<StockDto> getStockById(long id);
    Mono<StockDto>update(long id, StockDto stockDto);
    Mono<StockDto> save(StockDto stockDto);
}
