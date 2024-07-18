package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")})
public class Factura implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numero_factura")
    @JsonProperty("numeroFactura")
    private Integer numeroFactura;

    @NotEmpty(message = "La descripción no puede estar vacía")
    @Column(name = "descripcion_servicio")
    @JsonProperty("descripcionServicio")
    private String descripcionServicio;

    @NotEmpty(message = "El campo de valor no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El campo de valor debe ser positivo")
    @JsonProperty("valor")
    private Double valor;

    @NotEmpty(message = "El campo de total no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = false, message = "El campo de total debe ser positivo")
    @JsonProperty("total")
    private Double total;

    @JoinColumn(name = "paciente_id", referencedColumnName = "id_paciente")
    @ManyToOne
    @JsonProperty("paciente")
    private Paciente paciente;

    @Override
    public String toString() {
        return "Factura -> {" +
                " Número de Factura: '" + numeroFactura + "'\n" +
                " Descripción del Servicio: '" + descripcionServicio + "'\n" +
                " Valor: " + valor + "'\n" +
                " Total: " + total + '}' + "'\n" +
                " | Paciente Vinculado -> {" + paciente + "}" +
                '\n';
    }
}
