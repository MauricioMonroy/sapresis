package codelicht.sipressspringapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Integer id;
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String identificacion;
    private String telefono;
    private String email;
    private Boolean esPaciente;
    private Boolean esEmpleado;
}

