package com.example.cashsplash.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class Sale {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private UUID uuid;
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne
        @JoinColumn(name = "customer_id")
        private Customer customer;

        @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
        private List<SaleItem> items;

        @ManyToOne
        @JoinColumn(name = "campaign_id")
        private Campaign campaign;

        public Sale() {
                this.items = new ArrayList<>();
        }
}
