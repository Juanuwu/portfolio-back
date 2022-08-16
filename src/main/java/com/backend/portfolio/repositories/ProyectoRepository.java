package com.backend.portfolio.repositories;
import com.backend.portfolio.models.ProyectoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends CrudRepository<ProyectoModel, Long> {
}