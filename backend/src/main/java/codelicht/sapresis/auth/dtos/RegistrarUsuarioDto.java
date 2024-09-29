package codelicht.sapresis.auth.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para registrar un usuario
 * Realiza la transferencia de datos entre el controlador y el servicio
 */
@Getter
@Setter
public class RegistrarUsuarioDto {
    @NotNull
    @NotEmpty(message = "no puede estar vacío")
    private String email;

    @NotNull
    @NotEmpty(message = "no puede estar vacío")
    private String password;

    @NotNull
    @NotEmpty(message = "no puede estar vacío")
    private String nombreCompleto;
}
