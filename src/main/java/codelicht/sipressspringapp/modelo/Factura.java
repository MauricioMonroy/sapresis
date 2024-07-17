package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @JsonProperty("paciente")
    private Paciente paciente;

    @Override
    public String toString() {
        return "Factura{" +
                "numeroFactura=" + numeroFactura +
                ", descripcionServicio='" + descripcionServicio + '\'' +
                ", valor=" + valor +
                ", total=" + total +
                ", paciente=" + paciente +
                '}';
    }
}
