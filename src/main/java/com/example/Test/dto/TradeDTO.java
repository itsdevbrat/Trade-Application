package com.example.Test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class TradeDTO {

    private String tradeId;

    private Integer version;

    private String counterPartyId;

    private String bookId;

    private LocalDate maturityDate;

    private LocalDate createdDate;

    private boolean expired;

}
