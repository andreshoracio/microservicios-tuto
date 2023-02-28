package com.example.carroservice.services;

import com.example.carroservice.entities.Carro;
import com.example.carroservice.respositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired private CarroRepository carroRepository;

    public List<Carro> getCarros(){
        return carroRepository.findAll();
    }

    public Carro getCarro(int id){
        return carroRepository.findById(id).get();
    }

    public Carro saveCarro(Carro carro){
        return carroRepository.save(carro);
    }

    public List<Carro> getCarroByUserId(int id){
        return carroRepository.findByUsuarioId(id);
    }
}
