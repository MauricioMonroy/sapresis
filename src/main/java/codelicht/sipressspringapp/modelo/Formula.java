package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
        @NamedQuery(name = "Formula.findAll", query = "SELECT f FROM Formula f")})
public class Formula implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numero_formula")
    @JsonProperty("numeroFormula")
    private Integer numeroFormula;

    @Column(name = "nombre_medicacion")
    @JsonProperty("nombreMedicacion")
    private String nombreMedicacion;

    @Column(name = "fecha_medicacion")
    @Temporal(TemporalType.DATE)
    @JsonProperty("fechaMedicacion")
    private Date fechaMedicacion;

    @Column(name = "costo_medicacion")
    @JsonProperty("costoMedicacion")
    private Double costoMedicacion;

    @JoinColumn(name = "paciente_id", referencedColumnName = "id_paciente")
    @ManyToOne
    @JsonManagedReference
    @JsonProperty("paciente")
    private Paciente paciente;

    @Override
    public String toString() {
        return "Formula{" +
                "numeroFormula=" + numeroFormula +
                ", nombreMedicacion='" + nombreMedicacion + '\'' +
                ", fechaMedicacion=" + fechaMedicacion +
                ", costoMedicacion=" + costoMedicacion +
                ", paciente=" + paciente +
                '}';
    }
}
