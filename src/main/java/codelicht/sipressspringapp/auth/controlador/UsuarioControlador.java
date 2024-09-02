package codelicht.sipressspringapp.auth.controlador;

import codelicht.sipressspringapp.auth.entidad.Usuario;
import codelicht.sipressspringapp.auth.servicio.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/todos")
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    public ResponseEntity<List<Usuario>> allUsers() {
        List<Usuario> usuarios = userService.allUsers();

        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioActualizado) {
        Usuario usuarioExistente = userService.findById(id);

        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioExistente.setNombreCompleto(usuarioActualizado.getNombreCompleto());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setRole(usuarioActualizado.getRole());
        if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isEmpty()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
        }

        Usuario usuarioGuardado = userService.saveUsuario(usuarioExistente);

        return ResponseEntity.ok(usuarioGuardado);
    }

}
