package com.example.Test.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trades_tbl")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NonNull
    @Column(name = "trade_id", nullable = false)
    private String tradeId;

    @NonNull
    @Column(name = "version")
    private Integer version;

    @NonNull
    @Column(name = "counter_party_id", nullable = false)
    private String counterPartyId;

    @NonNull
    @Column(name = "book_id", nullable = false)
    private String bookId;

    @NonNull
    @Column(name = "maturity_date", nullable = false)
    private LocalDate maturityDate;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "expired", nullable = false)
    private Boolean expired = false;

}
