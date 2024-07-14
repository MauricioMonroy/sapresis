package codelicht.sipressspringapp.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "historial")
public class Historial extends Paciente {
    @Id
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Paciente paciente;

    @Column(name = "motivo_consulta")
    private String motivoConsulta;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Lob
    @Column(name = "sexo")
    private String sexo;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "ocupacion", length = 100)
    private String ocupacion;

    @Column(name = "contacto_emergencia", length = 15)
    private String contactoEmergencia;

    @Column(name = "nombre_contacto_emergencia", length = 45)
    private String nombreContactoEmergencia;

    @Lob
    @Column(name = "alergias")
    private String alergias;

    @Lob
    @Column(name = "condiciones_preexistentes")
    private String condicionesPreexistentes;

    @Lob
    @Column(name = "medicamentos_actuales")
    private String medicamentosActuales;

    @Lob
    @Column(name = "historial_vacunas")
    private String historialVacunas;

    @Lob
    @Column(name = "grupo_sanguineo")
    private String grupoSanguineo;

    @Lob
    @Column(name = "notas_adicionales")
    private String notasAdicionales;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ultima_actualizacion")
    private Instant ultimaActualizacion;

}