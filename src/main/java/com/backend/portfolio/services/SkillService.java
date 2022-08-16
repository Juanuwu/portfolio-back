package com.backend.portfolio.services;
import com.backend.portfolio.models.SkillModel;
import com.backend.portfolio.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class SkillService {
    @Autowired
    SkillRepository skillRepository;

    public ArrayList<SkillModel> obtenerSkills(){
        return(ArrayList<SkillModel>)skillRepository.findAll();
    }

    public SkillModel guardarSkill(SkillModel skill){return skillRepository.save(skill);
    }

    public void borrarSkill(Long id) {
        Optional<SkillModel> skill = skillRepository.findById(id);
        skillRepository.deleteById(id);
    }




}