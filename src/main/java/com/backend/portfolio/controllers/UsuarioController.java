package com.backend.portfolio.controllers;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.backend.portfolio.models.RoleModel;
import com.backend.portfolio.models.UsuarioModel;
import com.backend.portfolio.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping("/usuarios/userList")
    public ResponseEntity<List<UsuarioModel>> getUsers() {
        return ResponseEntity.ok().body(usuarioService.getUsers());
    }

    @PostMapping("/usuarios/save")
    public ResponseEntity<UsuarioModel> saveUser(@RequestBody UsuarioModel usuarioModel) {
        return ResponseEntity.ok().body(usuarioService.saveUser(usuarioModel));
    }

    @PostMapping("/usuarios/saveRole")
    public ResponseEntity<RoleModel> saveRole(@RequestBody RoleModel roleModel) {
        return ResponseEntity.ok().body(usuarioService.saveRole(roleModel));
    }

    @PostMapping("/usuarios/addRole")
    public ResponseEntity<?> addRole(@RequestBody RoleToUserForm form) {
        usuarioService.addRole(form.getRoleName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String autorizationHeader = null;
        Cookie[] cookies = request.getCookies();
        if (cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals("refresh_token")) {
                    autorizationHeader = c.getValue();
                }
            }
        }
            if (autorizationHeader != null) {
                try {
                    String refresh_token = autorizationHeader;
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(refresh_token);
                    String username = decodedJWT.getSubject();
                    UsuarioModel usuarioModel = usuarioService.getUser(username);
                    String access_token = JWT.create()
                            .withSubject(usuarioModel.getUsername())
                            .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                            .withIssuer(request.getRequestURL().toString())
                            .withClaim("roles", usuarioModel.getRoles().stream().map(RoleModel::getName).collect(Collectors.toList()))
                            .sign(algorithm);
                    final ResponseCookie access = ResponseCookie
                            .from("access_token", access_token)
                            .secure(true)
                            .httpOnly(true)
                            .path("/")
                            .sameSite("none")
                            .build();
                    response.addHeader(HttpHeaders.SET_COOKIE, access.toString());
                } catch (Exception exception) {
                    log.error("Error logging in: {}", exception.getMessage());
                    response.setHeader("error", exception.getMessage());
                    response.setStatus((FORBIDDEN.value()));
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                throw new RuntimeException("no refresh token :(");
            }
        }

    }

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}

