package com.backend.portfolio.repositories;
import com.backend.portfolio.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    RoleModel findByName (String name);
}
