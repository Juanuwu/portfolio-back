package com.backend.portfolio.services;
import com.backend.portfolio.models.EducacionModel;
import com.backend.portfolio.models.SkillModel;
import com.backend.portfolio.repositories.EducacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class EducacionService {
    @Autowired
    EducacionRepository educacionRepository;

    public ArrayList<EducacionModel> obtenerEducacion(){
        return(ArrayList<EducacionModel>)educacionRepository.findAll();
    }

    public EducacionModel guardarEducacion(EducacionModel educacion){
        return educacionRepository.save(educacion);
    }

    public void borrarEducacion (Long id) {
        Optional<EducacionModel> skill = educacionRepository.findById(id);
        educacionRepository.deleteById(id);
    }


}