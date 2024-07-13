package codelicht.sipressspringapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoDTO extends UsuarioDTO {
    private String cargo;
    private Double sueldo;
}
