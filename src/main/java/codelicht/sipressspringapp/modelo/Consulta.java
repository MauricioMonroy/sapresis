package codelicht.sipressspringapp.modelo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
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
    private Date fechaConsulta;

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
        return "Consulta -> {" +
                " Fecha de Consulta: '" + fechaConsulta + "'\n" +
                " Hora de consulta: '" + horaConsulta + '}' + "'\n" +
                " | Datos del Doctor -> {" + doctor + "}" + "'\n" +
                " | Datos del Paciente -> {" + paciente + "}" +
                '\n';
    }
}
