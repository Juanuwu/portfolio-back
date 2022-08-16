package com.backend.portfolio.controllers;
import com.backend.portfolio.models.SkillModel;
import com.backend.portfolio.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    SkillService skillService;

    @GetMapping()
    public ArrayList<SkillModel> obtenerSkills(){
        return skillService.obtenerSkills();
    }

    @PostMapping()
    public SkillModel guardarSkill(@RequestBody SkillModel skill){
        return this.skillService.guardarSkill(skill);
    }

    @DeleteMapping("/skill/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        skillService.borrarSkill(id);
        return new ResponseEntity<String>("Project was deleted successfully.", HttpStatus.OK);
    }

}

