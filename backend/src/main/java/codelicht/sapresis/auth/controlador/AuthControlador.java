package codelicht.sapresis.auth.controlador;

import codelicht.sapresis.auth.dtos.LoginResponse;
import codelicht.sapresis.auth.dtos.LoginUsuarioDto;
import codelicht.sapresis.auth.dtos.RegistrarUsuarioDto;
import codelicht.sapresis.auth.entidad.Usuario;
import codelicht.sapresis.auth.servicio.AuthenticationService;
import codelicht.sapresis.auth.servicio.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador para la autenticación de usuarios
 * Proporciona métodos para la gestión de registros y asignación de tokens JWT
 */
@RequestMapping("/sapresis/auth")
@RestController
@CrossOrigin(value = "http://localhost:3000")
public class AuthControlador {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthControlador(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody RegistrarUsuarioDto registrarUsuarioDto) {
        Usuario usuarioRegistrado = authenticationService.registrar(registrarUsuarioDto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Usuario registrado exitosamente");
        response.put("usuario", usuarioRegistrado);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> autenticar(@RequestBody LoginUsuarioDto loginUsuarioDto) {
        Usuario usuarioAutenticado = authenticationService.autenticar(loginUsuarioDto);

        String jwtToken = jwtService.generateToken(usuarioAutenticado);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok().build();
    }

}
