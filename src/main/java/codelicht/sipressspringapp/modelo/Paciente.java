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
    @Id
    @Column(name = "id_paciente", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @Column(name = "detalle_eps", length = 45)
    private String detalleEps;

    @Column(name = "fecha_consulta")
    private LocalDate fechaConsulta;

}