package com.example.Test.schedulers;

import com.example.Test.repository.TradeRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ExpiredFlagUpdatingScheduler {

    final TradeRepository tradeRepository;

    public ExpiredFlagUpdatingScheduler(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Scheduled(cron = "0 1 0 * * *")
    public void updateExpiredFlag() {
        System.out.println("cron--------------------------" + LocalDateTime.now());
        tradeRepository.findAll().forEach(trade -> {
            if (trade.getMaturityDate().isBefore(LocalDate.now())){
                tradeRepository.markExpired(trade.getId());
            }
        });
    }

}
