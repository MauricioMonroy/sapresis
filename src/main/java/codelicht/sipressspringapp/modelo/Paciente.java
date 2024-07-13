package codelicht.sipressspringapp.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "paciente")
@PrimaryKeyJoinColumn(name = "id_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Paciente extends Usuario {

    // Campo detalleEps con una longitud máxima de 45 caracteres
    @Column(name = "detalle_eps", length = 45)
    private String detalleEps;

    // Campo fechaConsulta mapeado a la columna fecha_consulta en la base de datos
    @Column(name = "fecha_consulta")
    private LocalDate fechaConsulta;

}

