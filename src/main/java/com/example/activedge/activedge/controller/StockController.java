package com.example.activedge.activedge.controller;

import com.example.activedge.activedge.entity.Stock;
import com.example.activedge.activedge.dto.StockDto;
import com.example.activedge.activedge.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping("/api/stocks")
    public ResponseEntity<?> create(@RequestBody StockDto stockDto){
        Mono<StockDto> stockResponse = stockService.save(stockDto);
        return new ResponseEntity<>(stockResponse, HttpStatus.CREATED);
    }
    @PutMapping("/api/stocks/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StockDto stockDto){
        Mono<StockDto> stockResponse = stockService.update(id, stockDto);
        if (!ObjectUtils.isEmpty(stockResponse)){
            return new ResponseEntity<>(stockResponse, OK);
        }else {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }
    @GetMapping("/api/stocks/{id}")
    public ResponseEntity<?> getStockById(@PathVariable long id){
        Mono<StockDto> stockResponse = stockService.getStockById(id);
        if (!ObjectUtils.isEmpty(stockResponse)){
            return new ResponseEntity<>(stockResponse, OK);
        }else {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @GetMapping("/api/stocks")
    public ResponseEntity<?> getStocks(){
        Flux<Stock> stockFlux = stockService.getAllStocks();
        return new ResponseEntity<>(stockFlux, OK);
    }
    @GetMapping("/hi")
    public String sayHello(){
        String holla = "Hello David";
        return holla;
    }

}
