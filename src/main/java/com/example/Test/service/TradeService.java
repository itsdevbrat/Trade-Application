package com.example.Test.service;

import com.example.Test.dto.TradeDTO;
import com.example.Test.entities.Trade;

import java.util.List;

public interface TradeService {
    public List<Trade> getAllTrades();

    Trade createATrade(Trade trade);
}
