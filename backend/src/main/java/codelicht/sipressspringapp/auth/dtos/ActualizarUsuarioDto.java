package codelicht.sipressspringapp.auth.dtos;

import codelicht.sipressspringapp.auth.entidad.Role;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para actualizar un usuario
 * Realiza la transferencia de datos entre el controlador y el servicio
 */
@Getter
@Setter
public class ActualizarUsuarioDto {
    private String nombreCompleto;
    private String email;
    private String password;
    private Role role;
}
