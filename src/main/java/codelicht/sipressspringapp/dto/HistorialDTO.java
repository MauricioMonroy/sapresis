package codelicht.sipressspringapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class HistorialDTO {
    private Integer id;
    private String motivoConsulta;
    private LocalDate fechaNacimiento;
    private String sexo;
    private String direccion;
    private String ocupacion;
    private String contactoEmergencia;
    private String nombreContactoEmergencia;
    private String alergias;
    private String condicionesPreexistentes;
    private String medicamentosActuales;
    private String historialVacunas;
    private String grupoSanguineo;
    private String notasAdicionales;
    private Instant ultimaActualizacion;
}
