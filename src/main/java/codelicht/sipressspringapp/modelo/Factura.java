package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")})
// @JsonIgnoreProperties({"paciente"})
public class Factura implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numero_factura")
    @JsonProperty("numeroFactura")
    private Integer numeroFactura;

    @Column(name = "descripcion_servicio")
    @JsonProperty("descripcionServicio")
    private String descripcionServicio;

    @JsonProperty("valor")
    private Double valor;

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
