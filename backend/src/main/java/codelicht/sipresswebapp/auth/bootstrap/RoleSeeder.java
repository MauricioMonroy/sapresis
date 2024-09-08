package codelicht.sipresswebapp.auth.bootstrap;

import codelicht.sipresswebapp.auth.entidad.Role;
import codelicht.sipresswebapp.auth.entidad.RoleEnum;
import codelicht.sipresswebapp.auth.repositorio.RoleRepositorio;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * Clase para crear los roles en la base de datos
 * Implementa ApplicationListener para ser ejecutado al iniciar la aplicación
 */
@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepositorio roleRepositorio;

    public RoleSeeder(RoleRepositorio roleRepositorio) {
        this.roleRepositorio = roleRepositorio;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.cargarRoles();
    }

    private void cargarRoles() {
        RoleEnum[] roleNames = new RoleEnum[]{RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPERADMIN};
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.USER, "Rol de usuario",
                RoleEnum.ADMIN, "Rol de administrador",
                RoleEnum.SUPERADMIN, "Rol de super administrador"
        );

        Arrays.stream(roleNames).forEach((nombreRol) -> {
            Optional<Role> optionalRole = this.roleRepositorio.findByNombre(nombreRol);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleCreated = new Role();

                roleCreated.setNombre(nombreRol);
                roleCreated.setDescripcion(roleDescriptionMap.get(nombreRol));

                roleRepositorio.save(roleCreated);
            });
        });
    }
}
