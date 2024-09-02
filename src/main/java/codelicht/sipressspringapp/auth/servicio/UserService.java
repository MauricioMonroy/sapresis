package codelicht.sipressspringapp.auth.servicio;

import codelicht.sipressspringapp.auth.dtos.RegistrarUsuarioDto;
import codelicht.sipressspringapp.auth.entidad.Role;
import codelicht.sipressspringapp.auth.entidad.RoleEnum;
import codelicht.sipressspringapp.auth.entidad.Usuario;
import codelicht.sipressspringapp.auth.repositorio.RoleRepositorio;
import codelicht.sipressspringapp.auth.repositorio.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la entidad User
 * Proporciona métodos para obtener todos los usuarios
 */
@Service
public class UserService {
    private final UsuarioRepositorio usuarioRepositorio;
    private final RoleRepositorio roleRepositorio;
    private final PasswordEncoder passwordEncoder;

    public UserService(UsuarioRepositorio usuarioRepositorio,
                       RoleRepositorio roleRepositorio,
                       PasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.roleRepositorio = roleRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> allUsers() {
        List<Usuario> usuarios = new ArrayList<>();

        usuarioRepositorio.findAll().forEach(usuarios::add);

        return usuarios;
    }

    public Usuario findById(Integer id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    public Usuario saveUsuario(Usuario usuario) {
        // Aquí puedes agregar lógica adicional si es necesario
        return usuarioRepositorio.save(usuario);
    }

    public Usuario crearAdministrador(RegistrarUsuarioDto input) {
        Optional<Role> optionalRole = roleRepositorio.findByNombre(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var usuario = new Usuario();
        usuario.setNombreCompleto(input.getNombreCompleto());
        usuario.setEmail(input.getEmail());
        usuario.setPassword(passwordEncoder.encode(input.getPassword()));
        usuario.setRole(optionalRole.get());

        return usuarioRepositorio.save(usuario);

    }
}
