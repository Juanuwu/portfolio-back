package com.backend.portfolio.controllers;
import com.backend.portfolio.models.ProyectoModel;
import com.backend.portfolio.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    ProyectoService proyectoService;

    @GetMapping()
    public ArrayList<ProyectoModel> obtenerProyectos(){
        return proyectoService.obtenerProyecto();
    }

    @PostMapping()
    public ProyectoModel guardarProyecto(@RequestBody ProyectoModel proyecto){
        return this.proyectoService.guardarProyecto(proyecto);
    }

    @DeleteMapping("/proyecto/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        proyectoService.borrarProyecto(id);
        return new ResponseEntity<String>("Skill was deleted successfully.", HttpStatus.OK);
    }

}