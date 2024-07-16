package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p")})
@JsonIgnoreProperties({"facturaList", "formulaList", "consultaList", "consultorioList"})
public class Paciente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_paciente")
    @JsonProperty("idPaciente")
    private Integer idPaciente;
    @Column(name = "nombre_paciente")
    @JsonProperty("nombrePaciente")
    private String nombrePaciente;
    @Column(name = "apellido_paciente")
    @JsonProperty("apellidoPaciente")
    private String apellidoPaciente;
    @Column(name = "direccion_paciente")
    @JsonProperty("direccionPaciente")
    private String direccionPaciente;
    @Column(name = "telefono_paciente")
    @JsonProperty("telefonoPaciente")
    private String telefonoPaciente;
    @Column(name = "email_paciente")
    @JsonProperty("emailPaciente")
    private String emailPaciente;
    @OneToMany(mappedBy = "paciente")
    @JsonManagedReference
    private List<Factura> facturaList;
    @JoinColumn(name = "eps_id", referencedColumnName = "id_eps")
    @ManyToOne
    @JsonBackReference
    private Eps eps;
    @OneToMany(mappedBy = "paciente")
    @JsonManagedReference
    private List<Formula> formulaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    @JsonManagedReference
    private List<Consulta> consultaList;
    @OneToMany(mappedBy = "paciente")
    @JsonManagedReference
    private List<Consultorio> consultorioList;

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", nombrePaciente='" + nombrePaciente + '\'' +
                ", apellidoPaciente='" + apellidoPaciente + '\'' +
                ", direccionPaciente='" + direccionPaciente + '\'' +
                ", telefonoPaciente='" + telefonoPaciente + '\'' +
                ", emailPaciente='" + emailPaciente + '\'' +
                ", eps='" + eps + '\'' +
                '}';
    }

}