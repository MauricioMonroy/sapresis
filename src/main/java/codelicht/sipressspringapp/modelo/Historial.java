package codelicht.sipressspringapp.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "historial")
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Añadir generación automática de ID
    @Column(name = "id_historial", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false) // Asegurar que el paciente siempre esté presente
    private Paciente idPaciente;

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

    @Override
    public String toString() {
        return "Historial{" +
                "id=" + id +
                ", motivoConsulta='" + motivoConsulta + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", sexo='" + sexo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ocupacion='" + ocupacion + '\'' +
                ", contactoEmergencia='" + contactoEmergencia + '\'' +
                ", nombreContactoEmergencia='" + nombreContactoEmergencia + '\'' +
                ", alergias='" + alergias + '\'' +
                ", condicionesPreexistentes='" + condicionesPreexistentes + '\'' +
                ", medicamentosActuales='" + medicamentosActuales + '\'' +
                ", historialVacunas='" + historialVacunas + '\'' +
                ", grupoSanguineo='" + grupoSanguineo + '\'' +
                ", notasAdicionales='" + notasAdicionales + '\'' +
                ", ultimaActualizacion=" + ultimaActualizacion +
                '}';
    }
}

