package com.example.Test.repository;

import com.example.Test.entities.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    List<Trade> findAllByTradeId(String tradeId);

    @Modifying
    @Transactional
    @Query("UPDATE Trade t " +
            "SET t.tradeId = :tradeId, " +
            "t.createdDate = :createdDate, " +
            "t.maturityDate = :maturityDate, " +
            "t.version = :version, " +
            "t.bookId = :bookId, " +
            "t.counterPartyId = :counterPartyId, " +
            "t.expired = :expired WHERE t.id = :id")
    void updateTrade(Long id, String tradeId, LocalDate createdDate, LocalDate maturityDate, int version, String bookId, String counterPartyId, Boolean expired);

    @Modifying
    @Transactional
    @Query("UPDATE Trade t " +
            "SET expired = true " +
            "WHERE t.id = :id")
    void markExpired(Long id);
}
