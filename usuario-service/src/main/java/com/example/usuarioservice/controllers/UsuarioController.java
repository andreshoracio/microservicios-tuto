package com.example.usuarioservice.controllers;

import com.example.usuarioservice.entities.Usuario;
import com.example.usuarioservice.modelos.Carro;
import com.example.usuarioservice.modelos.Moto;
import com.example.usuarioservice.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.obtenerUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUser(@PathVariable("id") int id){
        return ResponseEntity.ok(usuarioService.getUsuario(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.saveUser(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> getCarros(@PathVariable("usuarioId") int id){
        Usuario usuario = usuarioService.getUsuario(id);
        return usuario == null ? ResponseEntity.notFound().build() : ResponseEntity
                .ok(usuarioService.getCarros(id));
    }

    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable("usuarioId") int id){
        Usuario usuario = usuarioService.getUsuario(id);
        return usuario == null ? ResponseEntity.notFound().build() : ResponseEntity
                .ok(usuarioService.getMotos(id));
    }

    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") int id, @RequestBody Carro carro){
        return new ResponseEntity<>(usuarioService.saveCarro(id, carro), HttpStatus.OK);
    }

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int id, @RequestBody Moto moto){
        return ResponseEntity.ok(usuarioService.saveMoto(id, moto));
    }

    @GetMapping("/vehiculos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarVehiculos(@PathVariable("usuarioId") int id){
        return ResponseEntity.ok(usuarioService.getUsuarioAndVehiculos(id));
    }

}
