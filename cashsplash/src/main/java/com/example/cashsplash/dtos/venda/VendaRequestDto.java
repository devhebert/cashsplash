package com.example.cashsplash.dtos.venda;

import com.example.cashsplash.models.Campaign;
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
    private Campaign idCampaign;
}
