package com.example.cashsplash.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@Table(name = "venda")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class Venda {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
        @Column
        private UUID uuid;
        @ManyToOne
        @JoinColumn(name = "id_usuario")
        private User idUsuario;

        @ManyToOne
        @JoinColumn(name = "id_cliente")
        private Cliente idCliente;

        @ManyToOne
        @JoinColumn(name = "id_produto")
        private Produto idProduto;

        @ManyToOne
        @JoinColumn(name = "id_campaign")
        private Campaign idCampaign;
}
