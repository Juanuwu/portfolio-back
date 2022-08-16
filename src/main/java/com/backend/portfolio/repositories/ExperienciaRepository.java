package com.backend.portfolio.repositories;
import com.backend.portfolio.models.ExperienciaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaRepository extends CrudRepository<ExperienciaModel, Long> {
}
