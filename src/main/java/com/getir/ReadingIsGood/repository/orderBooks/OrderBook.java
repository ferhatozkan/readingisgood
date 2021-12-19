package com.getir.ReadingIsGood.repository.orderBooks;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "OrderBook")
public class OrderBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "FKOrderId", nullable = false)
    private int orderId;
    @Column(name = "FKBookId", nullable = false)
    private int bookId;
    @Column(name = "Count", nullable = false)
    private int count;
    @Column(name = "Price", nullable = false)
    private double price;
    @Column(name = "CreatedOn", nullable = false)
    @CreationTimestamp
    private Date createdOn;
}
