package com.example.cashsplash.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private UUID uuid;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String telefone;
    @Column(name = "endereco")
    private Address endereco;
}
