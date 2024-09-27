package codelicht.sapresis.auth.bootstrap;

import codelicht.sapresis.auth.dtos.RegistrarUsuarioDto;
import codelicht.sapresis.auth.entidad.Role;
import codelicht.sapresis.auth.entidad.RoleEnum;
import codelicht.sapresis.auth.entidad.Usuario;
import codelicht.sapresis.auth.repositorio.RoleRepositorio;
import codelicht.sapresis.auth.repositorio.UsuarioRepositorio;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Clase para crear el superadministrador en la base de datos
 * Implementa ApplicationListener para ser ejecutado al iniciar la aplicación
 */
@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepositorio roleRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(RoleRepositorio roleRepositorio,
                       UsuarioRepositorio usuarioRepositorio,
                       PasswordEncoder passwordEncoder) {
        this.roleRepositorio = roleRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.crearSuperAdministrator();
    }

    private void crearSuperAdministrator() {
        RegistrarUsuarioDto userDto = new RegistrarUsuarioDto();
        userDto.setNombreCompleto("Super Admin");
        userDto.setEmail("super.admin@correo.com");
        userDto.setPassword("superadmin");

        Optional<Role> optionalRole = roleRepositorio.findByNombre(RoleEnum.SUPERADMIN);
        Optional<Usuario> optionalUser = usuarioRepositorio.findByEmail(userDto.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var usuario = new Usuario();
        usuario.setNombreCompleto(userDto.getNombreCompleto());
        usuario.setEmail(userDto.getEmail());
        usuario.setPassword(passwordEncoder.encode(userDto.getPassword()));
        usuario.setRole(optionalRole.get());

        usuarioRepositorio.save(usuario);
    }
}
