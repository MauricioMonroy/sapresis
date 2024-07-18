package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "Consultorio.findAll", query = "SELECT c FROM Consultorio c")})
public class Consultorio implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numero_consultorio")
    @JsonProperty("numeroConsultorio")
    @Pattern(regexp = "\\d+", message = "El número del consultorio debe tener un formato válido")
    private Integer numeroConsultorio;

    @NotNull(message = "La fecha de admisión no puede estar vacía")
    @Column(name = "fecha_admision")
    @Temporal(TemporalType.DATE)
    @JsonProperty("fechaAdmision")
    private Date fechaAdmision;

    @JoinColumn(name = "paciente_id", referencedColumnName = "id_paciente")
    @ManyToOne
    @NotNull(message = "El campo de ID Paciente no puede estar vacío")
    @JsonProperty("paciente")
    private Paciente paciente;

    @JoinColumn(name = "personal_id", referencedColumnName = "id_personal")
    @ManyToOne
    @NotNull(message = "El campo de ID Personal no puede estar vacío")
    @JsonProperty("personal")
    private Personal personal;

    @Override
    public String toString() {
        return "Consultorio -> {" +
                " Número de Consultorio: '" + numeroConsultorio + "'\n" +
                " Fecha de Admisión: '" + fechaAdmision + '}' + "'\n" +
                " | Datos del Paciente -> {" + paciente + "}" + "\n" +
                " | Datos del Personal -> {" + personal + "}" +
                '\n';
    }
}
