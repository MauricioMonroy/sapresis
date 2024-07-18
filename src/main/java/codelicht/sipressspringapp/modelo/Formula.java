package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
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
        @NamedQuery(name = "Formula.findAll", query = "SELECT f FROM Formula f")})
public class Formula implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "numero_formula")
    @JsonProperty("numeroFormula")
    @Pattern(regexp = "\\d+", message = "El número de la fórmula debe tener un formato válido")
    private Integer numeroFormula;

    @NotEmpty(message = "El nombre de la medicación no puede estar vacío")
    @Column(name = "nombre_medicacion")
    @JsonProperty("nombreMedicacion")
    private String nombreMedicacion;

    @NotNull(message = "La fecha de medicación no puede estar vacía")
    @Column(name = "fecha_medicacion")
    @Temporal(TemporalType.DATE)
    @JsonProperty("fechaMedicacion")
    private Date fechaMedicacion;

    @NotNull(message = "El costo de la medicación no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo de la medicación debe ser un valor positivo")
    @Column(name = "costo_medicacion")
    @JsonProperty("costoMedicacion")
    private Double costoMedicacion;

    @JoinColumn(name = "paciente_id", referencedColumnName = "id_paciente")
    @ManyToOne
    @NotNull(message = "El campo de ID Paciente no puede estar vacío")
    @JsonProperty("paciente")
    private Paciente paciente;

    @Override
    public String toString() {
        return "Formula -> {" +
                " Número de Fórmula: '" + numeroFormula + "'\n" +
                " Nombre de la Medicación: '" + nombreMedicacion + "'\n" +
                " Fecha de Emisión: " + fechaMedicacion + "'\n" +
                " Costo de la Medicación: " + costoMedicacion + '}' + "'\n" +
                " | Paciente Vinculado -> {" + paciente + "}" +
                '\n';
    }
}
