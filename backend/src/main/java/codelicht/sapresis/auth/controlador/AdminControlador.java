package codelicht.sapresis.auth.controlador;

import codelicht.sapresis.auth.dtos.RegistrarUsuarioDto;
import codelicht.sapresis.auth.entidad.Usuario;
import codelicht.sapresis.auth.servicio.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Gestiona las peticiones de los administradores
 * Proporciona métodos para la creación usuarios con roles de administrador
 */
@RestController
@RequestMapping("/sapresis/admin")
@CrossOrigin(value = "http://localhost:3000")
public class AdminControlador {

    private final UserService userService;

    public AdminControlador(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Usuario> createAdministrator(@RequestBody RegistrarUsuarioDto registrarUsuarioDto) {
        Usuario administradorCreado = userService.crearAdministrador(registrarUsuarioDto);

        return ResponseEntity.ok(administradorCreado);
    }
}
