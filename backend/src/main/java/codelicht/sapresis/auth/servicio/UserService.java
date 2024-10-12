package codelicht.sapresis.auth.servicio;

import codelicht.sapresis.auth.dtos.RegistrarUsuarioDto;
import codelicht.sapresis.auth.entidad.Role;
import codelicht.sapresis.auth.entidad.RoleEnum;
import codelicht.sapresis.auth.entidad.Usuario;
import codelicht.sapresis.auth.repositorio.RoleRepositorio;
import codelicht.sapresis.auth.repositorio.UsuarioRepositorio;
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

    public List<Role> allRoles() {
        List<Role> roles = new ArrayList<>();

        roleRepositorio.findAll().forEach(roles::add);

        return roles;
    }

    public Usuario findById(Integer id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email).orElse(null);
    }

    public Usuario saveUsuario(Usuario usuario) {
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

    public void eliminarUsuario(Usuario usuario) {
        usuarioRepositorio.delete(usuario);
    }

}
