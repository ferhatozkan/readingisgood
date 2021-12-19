package com.getir.ReadingIsGood.repository.book;

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
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Stock", nullable = false)
    private int stock;
    @Column(name = "Price", nullable = false)
    private double price;
    @Column(name = "CreatedOn", nullable = false)
    @CreationTimestamp
    private Date createdOn;
}
