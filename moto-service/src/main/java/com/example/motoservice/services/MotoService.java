package com.example.motoservice.services;

import com.example.motoservice.entities.Moto;
import com.example.motoservice.respositories.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired private MotoRepository motoRepository;

    public List<Moto> getMotos(){
        return motoRepository.findAll();
    }

    public Moto getMoto(int id){
        return motoRepository.findById(id).get();
    }

    public Moto saveMoto(Moto moto){
        return motoRepository.save(moto);
    }

    public List<Moto> getMotosByUserId(int id){
        return motoRepository.findMotoByUsuarioId(id);
    }

}
