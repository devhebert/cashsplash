package com.example.cashsplash.dtos.venda;

import com.example.cashsplash.models.Campanha;
import com.example.cashsplash.models.Cliente;
import com.example.cashsplash.models.Produto;
import com.example.cashsplash.models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VendaRequestDto {
    private User idUsuario;
    private Cliente idCliente;
    private Produto idProduto;
    private Campanha idCampanha;
}
