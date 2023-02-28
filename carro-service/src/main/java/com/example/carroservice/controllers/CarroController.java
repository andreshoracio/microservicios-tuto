package com.example.carroservice.controllers;

import com.example.carroservice.entities.Carro;
import com.example.carroservice.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> getCarros(){
        return ResponseEntity.ok(carroService.getCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getCarro(@PathVariable int id){
        return ResponseEntity.ok(carroService.getCarro(id));
    }

    @PostMapping
    public ResponseEntity<Carro> saveCarro(@RequestBody Carro carro){
        return ResponseEntity.status(HttpStatus.CREATED).body(carroService.saveCarro(carro));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> getCarrosByUsuarioId(@PathVariable("usuarioId") int id){
        return ResponseEntity.ok(carroService.getCarroByUserId(id));
    }

}
