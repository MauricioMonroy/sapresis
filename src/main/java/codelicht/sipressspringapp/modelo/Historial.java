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

    // Anotación @Id indica que este campo es la clave primaria de la entidad
    @Id
    @Column(name = "id_historial", nullable = false)
    private Integer id;

    // Anotación @OneToOne indica una relación de uno a uno con la entidad Paciente
    // fetch = FetchType.LAZY especifica que la relación se cargará cuando se acceda a ella por primera vez
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente idPaciente;

    // Campo motivoConsulta mapeado a la columna motivo_consulta en la base de datos
    @Column(name = "motivo_consulta")
    private String motivoConsulta;

    // Campo fechaNacimiento mapeado a la columna fecha_nacimiento en la base de datos
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    // Campo sexo, almacenado como Large Object (Lob)
    @Lob
    @Column(name = "sexo")
    private String sexo;

    // Campo direccion mapeado a la columna direccion en la base de datos
    @Column(name = "direccion")
    private String direccion;

    // Campo ocupacion con una longitud máxima de 100 caracteres
    @Column(name = "ocupacion", length = 100)
    private String ocupacion;

    // Campo contactoEmergencia con una longitud máxima de 15 caracteres
    @Column(name = "contacto_emergencia", length = 15)
    private String contactoEmergencia;

    // Campo nombreContactoEmergencia con una longitud máxima de 45 caracteres
    @Column(name = "nombre_contacto_emergencia", length = 45)
    private String nombreContactoEmergencia;

    // Campo alergias, almacenado como Large Object (Lob)
    @Lob
    @Column(name = "alergias")
    private String alergias;

    // Campo condicionesPreexistentes, almacenado como Large Object (Lob)
    @Lob
    @Column(name = "condiciones_preexistentes")
    private String condicionesPreexistentes;

    // Campo medicamentosActuales, almacenado como Large Object (Lob)
    @Lob
    @Column(name = "medicamentos_actuales")
    private String medicamentosActuales;

    // Campo historialVacunas, almacenado como Large Object (Lob)
    @Lob
    @Column(name = "historial_vacunas")
    private String historialVacunas;

    // Campo grupoSanguineo, almacenado como Large Object (Lob)
    @Lob
    @Column(name = "grupo_sanguineo")
    private String grupoSanguineo;

    // Campo notasAdicionales, almacenado como Large Object (Lob)
    @Lob
    @Column(name = "notas_adicionales")
    private String notasAdicionales;

    // Campo ultimaActualizacion con un valor predeterminado de la marca de tiempo actual
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "ultima_actualizacion")
    private Instant ultimaActualizacion;

    // Sobrescribimos el método toString para evitar la carga ´lazy´
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
