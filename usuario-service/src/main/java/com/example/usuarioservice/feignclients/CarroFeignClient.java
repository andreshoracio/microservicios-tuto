package com.example.usuarioservice.feignclients;

import com.example.usuarioservice.modelos.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "carro-service", url = "http://localhost:8082", path = "/carro")
//@RequestMapping("/carro")
public interface CarroFeignClient {

    @PostMapping
    Carro save(@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId}")
    List<Carro> getCarros(@PathVariable("usuarioId") int id);
}
