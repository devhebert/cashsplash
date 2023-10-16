package com.example.cashsplash.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@Table(name = "sale")
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
        @JoinColumn(name = "id_user")
        private User idUser;

        @ManyToOne
        @JoinColumn(name = "id_customer")
        private Customer idCustomer;

        @ManyToOne
        @JoinColumn(name = "id_product")
        private Product idProduct;

        @ManyToOne
        @JoinColumn(name = "id_campaign")
        private Campaign idCampaign;
}
