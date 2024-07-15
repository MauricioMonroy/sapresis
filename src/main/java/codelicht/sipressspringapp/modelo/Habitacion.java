package codelicht.sipressspringapp.modelo;

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
        @NamedQuery(name = "Habitacion.findAll", query = "SELECT h FROM Habitacion h")})
public class Habitacion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numero_habitacion")
    private Integer numeroHabitacion;
    @Column(name = "fecha_admision")
    @Temporal(TemporalType.DATE)
    private Date fechaAdmision;
    @JoinColumn(name = "paciente_id", referencedColumnName = "id_paciente")
    @ManyToOne
    private Paciente paciente;
    @JoinColumn(name = "personal_id", referencedColumnName = "id_personal")
    @ManyToOne
    private Personal personal;
}
