package codelicht.sipressspringapp.auth.controlador;

import codelicht.sipressspringapp.auth.entidad.Usuario;
import codelicht.sipressspringapp.auth.servicio.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador para la gestión de usuarios
 * Proporciona métodos para la obtención de información de usuarios autenticados y de todos los usuarios
 */
@RequestMapping("/sipress-app/usuarios")
@RestController
@CrossOrigin(value = "http://localhost:3000")
public class UsuarioControlador {
    private final UserService userService;

    public UsuarioControlador(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/perfil")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Usuario> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Usuario usuarioActual = (Usuario) authentication.getPrincipal();

        return ResponseEntity.ok(usuarioActual);
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<List<Usuario>> allUsers() {
        List<Usuario> usuarios = userService.allUsers();

        return ResponseEntity.ok(usuarios);
    }
}
