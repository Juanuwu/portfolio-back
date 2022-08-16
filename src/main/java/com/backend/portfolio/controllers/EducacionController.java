package com.backend.portfolio.controllers;
import com.backend.portfolio.models.EducacionModel;
import com.backend.portfolio.services.EducacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/educacion")
public class EducacionController {

    @Autowired
    EducacionService educacionService;

    @GetMapping()
    public ArrayList<EducacionModel> obtenerEducacion(){
        return educacionService.obtenerEducacion();
    }

    @PostMapping()
    public EducacionModel guardarEducacion(@RequestBody EducacionModel educacion){
        return this.educacionService.guardarEducacion(educacion);
    }

    @DeleteMapping("/educacion/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        educacionService.borrarEducacion(id);
        return new ResponseEntity<String>("Edu was deleted successfully.", HttpStatus.OK);
    }

}
