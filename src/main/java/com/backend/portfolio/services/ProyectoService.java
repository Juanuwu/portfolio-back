package com.backend.portfolio.services;
import com.backend.portfolio.models.ProyectoModel;
import com.backend.portfolio.repositories.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProyectoService {
    @Autowired
    ProyectoRepository proyectoRepository;

    public ArrayList<ProyectoModel> obtenerProyecto(){
        return(ArrayList<ProyectoModel>)proyectoRepository.findAll();
    }

    public ProyectoModel guardarProyecto(ProyectoModel proyecto){
        return proyectoRepository.save(proyecto);
    }

    public void borrarProyecto(Long id) {
        Optional<ProyectoModel> skill = proyectoRepository.findById(id);
        proyectoRepository.deleteById(id);
    }
}