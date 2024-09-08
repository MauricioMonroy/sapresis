package codelicht.sipresswebapp.auth.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para el login de un usuario
 * Realiza la transferencia de datos entre el controlador y el servicio
 */
@Getter
@Setter
public class LoginUsuarioDto {
    private String email;
    private String password;
}
