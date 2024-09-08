package codelicht.sipresswebapp.auth.servicio;

import codelicht.sipresswebapp.auth.dtos.LoginUsuarioDto;
import codelicht.sipresswebapp.auth.dtos.RegistrarUsuarioDto;
import codelicht.sipresswebapp.auth.entidad.Role;
import codelicht.sipresswebapp.auth.entidad.RoleEnum;
import codelicht.sipresswebapp.auth.entidad.Usuario;
import codelicht.sipresswebapp.auth.repositorio.RoleRepositorio;
import codelicht.sipresswebapp.auth.repositorio.UsuarioRepositorio;
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
    private final RoleRepositorio roleRepositorio;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UsuarioRepositorio usuarioRepositorio,
                                 RoleRepositorio roleRepositorio,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.roleRepositorio = roleRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    // Métodos de registro y autenticación
    public Usuario registrar(RegistrarUsuarioDto input) {
        Optional<Role> optionalRole = roleRepositorio.findByNombre(RoleEnum.USER);

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
