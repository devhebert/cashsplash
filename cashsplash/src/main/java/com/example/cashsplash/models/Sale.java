package com.example.cashsplash.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class Sale {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
        @Column
        private UUID uuid;
        @ManyToOne
        @JoinColumn(name = "user_Id")
        private User user;

        @OneToOne
        @JoinColumn(name = "customer_id")
        private Customer customer;

        @OneToMany
        @JoinColumn(name = "product_id")
        private List<Product> products;

        @ManyToOne
        @JoinColumn(name = "campaign_id")
        private Campaign campaign;
}
