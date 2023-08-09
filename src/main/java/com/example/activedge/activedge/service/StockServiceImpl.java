package com.example.activedge.activedge.service;

import com.example.activedge.activedge.dto.GeneralException;
import com.example.activedge.activedge.entity.Stock;
import com.example.activedge.activedge.dto.StockDto;
import com.example.activedge.activedge.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    @Override
    public Flux<Stock> getAllStocks() {
        Flux<Stock> listOfStocks = stockRepository.findAll();

        return listOfStocks;
    }

    @Override
    public Mono<StockDto> getStockById(long id) {
        Mono<Stock> fetchedStock = stockRepository.findById(id);
        return fetchedStock
            .flatMap(stock -> {
                if (ObjectUtils.isEmpty(stock)){
                    return Mono.error(new GeneralException("Id has no record"));
                }
                StockDto stockDTO =  mapToDto(stock);
                return Mono.just(stockDTO);
            }).onErrorComplete(throwable -> throwable instanceof GeneralException);
    }

    @Override
    public Mono<StockDto> update(long id, StockDto stockDto) {
        Mono<Stock> stockMono = stockRepository.findById(id);
        return stockMono
            .flatMap(existingStock -> {
                System.out.println("Checking existing + " +existingStock.toString());
                if (!ObjectUtils.isEmpty(stockDto)) {
                    if (!ObjectUtils.isEmpty(stockDto.getName())) {
                        existingStock.setName(stockDto.getName());
                    }
                    if (!ObjectUtils.isEmpty(stockDto.getAmount())) {
                      existingStock.setCurrentPrice(BigDecimal.valueOf(stockDto.getAmount()));
                    }
                    return stockRepository.save(existingStock)
                        .map(this::mapToDto);
                } else {
                    return Mono.error(new GeneralException("Stock not found with ID: " + id));
                }
            });
    }

    @Override
    public Mono<StockDto> save(StockDto stockDto) {
        Stock stock = mapDtoToStock(stockDto);
        Mono<Stock> savedStock = stockRepository.save(stock);
        return savedStock.flatMap(saved -> {
            StockDto savedStockDto = mapToDto(saved);
            return Mono.just(savedStockDto);
        });
    }


    private StockDto mapToDto(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setName(stock.getName());
        stockDto.setAmount(stock.getCurrentPrice().doubleValue());
        return stockDto;
    }

    private Stock mapDtoToStock(StockDto stockDto) {
        Stock stock = new Stock();
        stock.setName(stockDto.getName());
        stock.setCurrentPrice(BigDecimal.valueOf(stockDto.getAmount()));
        stock.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        stock.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        return stock;
    }
}
