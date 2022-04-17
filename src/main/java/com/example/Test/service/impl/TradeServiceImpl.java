package com.example.Test.service.impl;

import com.example.Test.entities.Trade;
import com.example.Test.exceptions.BadRequestException;
import com.example.Test.repository.TradeRepository;
import com.example.Test.service.TradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {

    final TradeRepository tradeRepository;

    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade createATrade(Trade incomingTrade) {
        validateMaturityDate(incomingTrade);

        incomingTrade.setCreatedDate(LocalDate.now(ZoneId.of("UTC")));
        Long updateTradeId = -1L;
        int smallestVersion = Integer.MAX_VALUE;

        List<Trade> trades = tradeRepository.findAllByTradeId(incomingTrade.getTradeId());
        for (Trade trade1 : trades) {
            if (trade1.getVersion() < smallestVersion)
                smallestVersion = trade1.getVersion();

            if (incomingTrade.getVersion().equals(trade1.getVersion())) {
                updateTradeId = trade1.getId();
            }
        }

        //If trades are present and incoming trade version is less than smallest version in present trades
        if (!trades.isEmpty() && incomingTrade.getVersion() < smallestVersion) {
            throw new BadRequestException("Trade version is less than the existing version");
        } else if (updateTradeId != -1L) {
            tradeRepository.updateTrade(updateTradeId, incomingTrade.getTradeId(), incomingTrade.getCreatedDate(), incomingTrade.getMaturityDate(), incomingTrade.getVersion(), incomingTrade.getBookId(), incomingTrade.getCounterPartyId(), incomingTrade.getExpired());
            return incomingTrade;
        } else
            return tradeRepository.save(incomingTrade);
    }

    private void validateMaturityDate(Trade incomingTrade) {
        if (incomingTrade.getMaturityDate().isBefore(LocalDate.now()))
            throw new BadRequestException("Trade maturity date less than todays date");
    }
}
