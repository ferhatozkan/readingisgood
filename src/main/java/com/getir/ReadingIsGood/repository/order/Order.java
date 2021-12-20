package com.getir.ReadingIsGood.repository.order;

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
@Table(name = "`Order`") // Orders because Order is a reserved word
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "FKCustomerId", nullable = false)
    private int customerId;
    @Column(name = "totalPrice", nullable = false)
    private double totalPrice;
    @Column(name = "CreatedOn", nullable = false)
    @CreationTimestamp
    private Date createdOn;
}
