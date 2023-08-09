package com.example.activedge.activedge.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
public class Stock implements Persistable<Long>{
    @org.springframework.data.annotation.Id
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp createDate;

    private Timestamp lastUpdate;
    private String name;
    private BigDecimal currentPrice;

    @Override
    public boolean isNew() {
        return id == null;
    }
}
