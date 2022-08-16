package com.backend.portfolio.repositories;
import com.backend.portfolio.models.InformacionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacionRepository extends CrudRepository<InformacionModel, Long> {
}