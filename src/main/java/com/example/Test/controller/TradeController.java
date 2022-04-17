package com.example.Test.controller;

import com.example.Test.dto.TradeDTO;
import com.example.Test.entities.Trade;
import com.example.Test.service.impl.TradeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/trade")
public class TradeController {

    final TradeServiceImpl tradeService;
    public TradeController(TradeServiceImpl tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TradeDTO>> getAllTrades() {
        List<Trade> allTrades = tradeService.getAllTrades();
        return ResponseEntity
                .ok()
                .body(allTrades
                        .stream()
                        .map(trade -> new TradeDTO(trade.getTradeId(), trade.getVersion(), trade.getCounterPartyId(), trade.getBookId(), trade.getMaturityDate(), trade.getCreatedDate(), trade.getExpired()))
                        .collect(Collectors.toList()));
    }

    @PostMapping("/")
    public ResponseEntity<String> createATrade(@RequestBody TradeDTO tradeDTO) {
        Trade trade = new Trade(tradeDTO.getTradeId(), tradeDTO.getVersion(), tradeDTO.getCounterPartyId(), tradeDTO.getBookId(), tradeDTO.getMaturityDate());
        Trade createdTrade = tradeService.createATrade(trade);
        return ResponseEntity.created(URI.create("/trade/"+createdTrade.getTradeId())).build();
    }
}
