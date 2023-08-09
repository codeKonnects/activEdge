package com.example.activedge.activedge;

import com.example.activedge.activedge.dto.StockDto;
import com.example.activedge.activedge.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Config implements CommandLineRunner {
    private final StockService stockService;

    @Override
    public void run(String... args) throws Exception {
        stockService.save(new StockDto(1100.00, "Active Edge")).subscribe();
        stockService.save(new StockDto(110.00, "David")).subscribe();
        System.out.println();
    }

}
