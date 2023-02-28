package com.example.motoservice.controller;

import com.example.motoservice.entities.Moto;
import com.example.motoservice.services.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired private MotoService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> getMotos(){
        return ResponseEntity.ok(motoService.getMotos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> getMoto(@PathVariable int id){
        return ResponseEntity.ok(motoService.getMoto(id));
    }

    @PostMapping
    public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto){
        return ResponseEntity.status(HttpStatus.CREATED).body(motoService.saveMoto(moto));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotosByUserId(@PathVariable("usuarioId") int id){
        return ResponseEntity.ok(motoService.getMotosByUserId(id));
    }

}
