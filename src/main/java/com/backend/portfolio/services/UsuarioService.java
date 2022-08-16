package com.backend.portfolio.services;
import com.backend.portfolio.models.RoleModel;
import com.backend.portfolio.models.UsuarioModel;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UsuarioService {


    UsuarioModel saveUser(UsuarioModel usuarioModel);
    RoleModel saveRole(RoleModel roleModel);
    void addRole(String username, String roleName);
    UsuarioModel getUser(String username);
    List<UsuarioModel> getUsers();


}
