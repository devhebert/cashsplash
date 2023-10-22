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
        @JoinColumn(name = "id_user", referencedColumnName = "id")
        private User user;

        @OneToOne
        @JoinColumn(name = "id_customer", referencedColumnName = "id")
        private Customer customer;

        @OneToMany
        @JoinColumn(name = "id_product", referencedColumnName = "id")
        private List<Product> products;

        @ManyToOne
        @JoinColumn(name = "id_campaign", referencedColumnName = "id")
        private Campaign campaign;
}
