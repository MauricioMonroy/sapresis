package codelicht.sipressspringapp.modelo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ConsultaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "paciente_id")
    private int pacienteId;
    @Basic(optional = false)
    @Column(name = "doctor_id")
    private int doctorId;
}
