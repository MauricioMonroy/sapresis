package codelicht.sipressspringapp.auth.controlador;

import codelicht.sipressspringapp.auth.dtos.LoginResponse;
import codelicht.sipressspringapp.auth.dtos.LoginUsuarioDto;
import codelicht.sipressspringapp.auth.dtos.RegistrarUsuarioDto;
import codelicht.sipressspringapp.auth.entidad.Usuario;
import codelicht.sipressspringapp.auth.servicio.AuthenticationService;
import codelicht.sipressspringapp.auth.servicio.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para la autenticación de usuarios
 * Proporciona métodos para la gestión de registros y asignación de tokens JWT
 */
@RequestMapping("/sipress-app/auth")
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
    public ResponseEntity<Usuario> registrar(@RequestBody RegistrarUsuarioDto registrarUsuarioDto) {
        Usuario usuarioRegistrado = authenticationService.registrar(registrarUsuarioDto);
        return ResponseEntity.ok(usuarioRegistrado);
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
}
