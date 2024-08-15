package codelicht.sipressspringapp.auth.servicio;

import codelicht.sipressspringapp.auth.dtos.LoginUsuarioDto;
import codelicht.sipressspringapp.auth.dtos.RegistrarUsuarioDto;
import codelicht.sipressspringapp.auth.entidad.Role;
import codelicht.sipressspringapp.auth.entidad.RoleEnum;
import codelicht.sipressspringapp.auth.entidad.Usuario;
import codelicht.sipressspringapp.auth.repositorio.RoleRepositorio;
import codelicht.sipressspringapp.auth.repositorio.UsuarioRepositorio;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servicio para la autenticación de usuarios
 * Proporciona métodos para registrar y autenticar usuarios
 */
@Service
public class AuthenticationService {
    private final UsuarioRepositorio usuarioRepositorio;
    private final RoleRepositorio RoleRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UsuarioRepositorio usuarioRepositorio,
                                 RoleRepositorio RoleRepositorio,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.RoleRepositorio = RoleRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    // Métodos de registro y autenticación
    public Usuario registrar(RegistrarUsuarioDto input) {
        Optional<Role> optionalRole = RoleRepositorio.findByNombre(RoleEnum.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(input.getNombreCompleto());
        usuario.setEmail(input.getEmail());
        usuario.setPassword(passwordEncoder.encode(input.getPassword()));
        usuario.setRole(optionalRole.get());

        return usuarioRepositorio.save(usuario);
    }

    public Usuario autenticar(LoginUsuarioDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return usuarioRepositorio.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
