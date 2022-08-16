package com.backend.portfolio.services;
import com.backend.portfolio.models.RoleModel;
import com.backend.portfolio.models.UsuarioModel;
import com.backend.portfolio.repositories.RoleRepository;
import com.backend.portfolio.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioModel saveUser(UsuarioModel usuarioModel) {
        usuarioModel.setPassword(passwordEncoder.encode(usuarioModel.getPassword()));
        return usuarioRepository.save(usuarioModel);
    }

    @Override
    public RoleModel saveRole(RoleModel roleModel) {
        return roleRepository.save(roleModel);
    }

    @Override
    public void addRole(String username, String roleName) {
        UsuarioModel usuarioModel = usuarioRepository.findByUsername(username);
        RoleModel roleModel = roleRepository.findByName(roleName);
        usuarioModel.getRoles().add(roleModel);
    }

    @Override
    public UsuarioModel getUser(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public List<UsuarioModel> getUsers() {
        return usuarioRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.findByUsername(username);
        if(usuarioModel == null){
            throw new UsernameNotFoundException("username not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        usuarioModel.getRoles().forEach(roleModel -> {authorities.add(new SimpleGrantedAuthority(roleModel.getName()));
        });
        return new User(usuarioModel.getUsername(), usuarioModel.getPassword(), authorities);
    }
}
