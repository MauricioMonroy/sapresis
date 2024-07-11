package codelicht.sipressspringapp.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "paciente")
public class Paciente {

    // Anotación @Id indica que este campo es la clave primaria de la entidad
    @Id
    @Column(name = "id_paciente", nullable = false)
    private Integer id;

    // Anotación @ManyToOne indica una relación de muchos a uno con la entidad Usuario
    // fetch = FetchType.LAZY especifica que la relación se cargará cuando se acceda a ella por primera vez
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    // Campo detalleEps con una longitud máxima de 45 caracteres
    @Column(name = "detalle_eps", length = 45)
    private String detalleEps;

    // Campo fechaConsulta mapeado a la columna fecha_consulta en la base de datos
    @Column(name = "fecha_consulta")
    private LocalDate fechaConsulta;

}
