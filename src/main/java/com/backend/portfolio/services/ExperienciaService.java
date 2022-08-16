package com.backend.portfolio.services;
import com.backend.portfolio.models.ExperienciaModel;
import com.backend.portfolio.models.ProyectoModel;
import com.backend.portfolio.repositories.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ExperienciaService {
    @Autowired
    ExperienciaRepository experienciaRepository;

    public ArrayList<ExperienciaModel> obtenerExperiencia(){
        return(ArrayList<ExperienciaModel>)experienciaRepository.findAll();
    }

    public ExperienciaModel guardarExperiencia(ExperienciaModel experiencia){
        return experienciaRepository.save(experiencia);
    }


    public void borrarExperiencia(Long id) {
        Optional<ExperienciaModel> skill = experienciaRepository.findById(id);
        experienciaRepository.deleteById(id);
    }
}