package codelicht.sapresis.auth.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para registrar un usuario
 * Realiza la transferencia de datos entre el controlador y el servicio
 */
@Getter
@Setter
public class RegistrarUsuarioDto {
    private String email;
    private String password;
    private String nombreCompleto;
}
