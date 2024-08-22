package codelicht.sipressspringapp.modelo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "Consulta.findAll", query = "SELECT c FROM Consulta c")})
public class Consulta implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsultaPK consultaPK;

    @Column(name = "fecha_consulta")
    @Temporal(TemporalType.DATE)
    @JsonProperty("fechaConsulta")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaConsulta;

    @Column(name = "hora_consulta")
    @Temporal(TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @JsonProperty("horaConsulta")
    private Date horaConsulta;

    @JoinColumn(name = "doctor_id", referencedColumnName = "id_doctor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonProperty("doctor")
    private Doctor doctor;

    @JoinColumn(name = "paciente_id", referencedColumnName = "id_paciente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonProperty("paciente")
    private Paciente paciente;

    @Override
    public String toString() {
        return "Consulta{\n" +
                "fechaConsulta=" + fechaConsulta + ",\n" +
                "horaConsulta=" + horaConsulta + ",\n" +
                "doctor=" + doctor + ",\n" +
                "paciente=" + paciente + "\n" +
                '}';
    }
}
