package com.example.usuarioservice.services;

import com.example.usuarioservice.entities.Usuario;
import com.example.usuarioservice.feignclients.CarroFeignClient;
import com.example.usuarioservice.feignclients.MotoFeignClient;
import com.example.usuarioservice.modelos.Carro;
import com.example.usuarioservice.modelos.Moto;
import com.example.usuarioservice.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class    UsuarioService {

    @Autowired private CarroFeignClient carroFeignClient;

    @Autowired private MotoFeignClient motoFeignClient;
    @Autowired private RestTemplate restTemplate;
    @Autowired private UsuarioRepository usuarioRepository;


    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuario(int id){
        return usuarioRepository.findById(id).get();
    }

    public Usuario saveUser(Usuario usuario){
        return usuarioRepository.save(usuario);
    }


    //====== METODOS RestTemplate ========
    public List<Carro> getCarros(int usuarioId){
        List<Carro> carros = restTemplate
                .getForObject("http://localhost:8082/carro/usuario/"+usuarioId, List.class);
        return carros;
    }

    public List<Moto> getMotos(int usuarioId){
        List<Moto> motos = restTemplate
                .getForObject("http://localhost:8083/moto/usuario/"+usuarioId, List.class);
        return motos;
    }

    //====== METODOS FeignClient ========
    public Carro saveCarro(int usuarioId, Carro carro){
        carro.setUsuarioId(usuarioId);
        return carroFeignClient.save(carro);
    }

    public Moto saveMoto(int usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        return motoFeignClient.save(moto);
    }

    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        Map<String, Object> resultado = new HashMap<>();

        if(usuario == null){
            resultado.put("Mensaje","El usuario no existe");
            return resultado;
        }

        resultado.put("Usuario", usuario);

        List<Carro> carros = carroFeignClient.getCarros(usuarioId);
        List<Moto> motos = motoFeignClient.getMotos((usuarioId));

       if(carros.isEmpty()){
         resultado.put("Carros", "No tiene");
       }else {
           resultado.put("Carros", carros);
       }

       if(motos.isEmpty()){
           resultado.put("Motos", "No tiene");
       }else{
           resultado.put("Motos", motos);
       }

        return resultado;
    }

}
