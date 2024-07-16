package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Integer numeroFactura;
    @Column(name = "descripcion_servicio")
    private String descripcionServicio;
    private Double valor;
    private Double total;
    @JoinColumn(name = "paciente_id", referencedColumnName = "id_paciente")
    @ManyToOne
    @JsonBackReference
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
