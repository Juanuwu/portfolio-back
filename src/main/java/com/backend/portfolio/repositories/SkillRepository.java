package com.backend.portfolio.repositories;
import com.backend.portfolio.models.SkillModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<SkillModel, Long> {
}