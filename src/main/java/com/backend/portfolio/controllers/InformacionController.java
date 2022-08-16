package com.backend.portfolio.controllers;
import com.backend.portfolio.models.InformacionModel;
import com.backend.portfolio.services.InformacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/informacion")
public class InformacionController {

    @Autowired
    InformacionService informacionService;

    @GetMapping()
    public ArrayList<InformacionModel> obtenerInformacion(){
        return informacionService.obtenerInformacion();
    }

    @PostMapping()
    public InformacionModel guardarInformacion(@RequestBody InformacionModel informacion){
        return this.informacionService.guardarInformacion(informacion);
    }

}
