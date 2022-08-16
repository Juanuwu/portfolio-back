package com.backend.portfolio.services;
import com.backend.portfolio.models.InformacionModel;
import com.backend.portfolio.repositories.InformacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class InformacionService {
    @Autowired
    InformacionRepository informacionRepository;

    public ArrayList<InformacionModel> obtenerInformacion(){
        return(ArrayList<InformacionModel>)informacionRepository.findAll();
    }

    public InformacionModel guardarInformacion(InformacionModel informacion){
        return informacionRepository.save(informacion);
    }


}