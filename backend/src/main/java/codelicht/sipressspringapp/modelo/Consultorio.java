package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

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
    private Integer numeroConsultorio;

    @Column(name = "fecha_admision")
    @Temporal(TemporalType.DATE)
    @JsonProperty("fechaAdmision")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaAdmision;

    @JoinColumn(name = "paciente_id", referencedColumnName = "id_paciente")
    @ManyToOne
    @JsonProperty("paciente")
    private Paciente paciente;

    @JoinColumn(name = "personal_id", referencedColumnName = "id_personal")
    @ManyToOne
    @JsonProperty("personal")
    private Personal personal;

    @Override
    public String toString() {
        return "Consultorio{\n" +
                "numeroConsultorio=" + numeroConsultorio + ",\n" +
                "fechaAdmision=" + fechaAdmision + ",\n" +
                "paciente=" + paciente + ",\n" +
                "personal=" + personal + "\n" +
                '}';
    }
}
