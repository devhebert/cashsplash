package com.example.cashsplash.feign;

import com.example.cashsplash.dtos.viacep.ViaCepResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "FeignZipCode", url = "${url.zipcode}")
public interface FeignZipCode {

    @GetMapping("/{cep}/json/")
    ViaCepResponseDTO fetchCep(@PathVariable("cep") String cep);
}
