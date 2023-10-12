package com.example.cashsplash.services.viacep;

import com.example.cashsplash.dtos.viacep.ViaCepResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {
    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    public ViaCepResponseDTO fetchCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(VIA_CEP_URL, ViaCepResponseDTO.class, cep);
    }
}