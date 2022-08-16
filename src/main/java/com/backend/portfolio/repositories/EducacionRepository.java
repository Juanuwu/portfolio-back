package com.backend.portfolio.repositories;
import com.backend.portfolio.models.EducacionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducacionRepository extends CrudRepository<EducacionModel, Long> {
}
