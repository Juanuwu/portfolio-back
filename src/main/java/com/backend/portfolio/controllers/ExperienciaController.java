package com.backend.portfolio.controllers;
import com.backend.portfolio.models.ExperienciaModel;
import com.backend.portfolio.services.ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/experiencia")
public class ExperienciaController {

    @Autowired
    ExperienciaService experienciaService;

    @GetMapping()
    public ArrayList<ExperienciaModel> obtenerExperiencia(){
        return experienciaService.obtenerExperiencia();
    }

    @PostMapping()
    public ExperienciaModel guardarExperiencia(@RequestBody ExperienciaModel experiencia){
        return this.experienciaService.guardarExperiencia(experiencia);
    }

    @DeleteMapping("/experiencia/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        experienciaService.borrarExperiencia(id);
        return new ResponseEntity<String>("Experience was deleted successfully.", HttpStatus.OK);
    }

}
