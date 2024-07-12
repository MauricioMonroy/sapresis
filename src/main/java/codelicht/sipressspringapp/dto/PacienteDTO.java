package codelicht.sipressspringapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PacienteDTO {
    private Integer id;
    private String detalleEps;
    private LocalDate fechaConsulta;

}
