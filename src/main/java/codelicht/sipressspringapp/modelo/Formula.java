package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Integer numeroFormula;
    @Column(name = "nombre_medicacion")
    private String nombreMedicacion;
    @Column(name = "fecha_medicacion")
    @Temporal(TemporalType.DATE)
    private Date fechaMedicacion;
    @Column(name = "costo_medicacion")
    private Double costoMedicacion;
    @JoinColumn(name = "paciente_id", referencedColumnName = "id_paciente")
    @ManyToOne
    @JsonBackReference
    private Paciente paciente;

}
