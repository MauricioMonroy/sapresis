package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
@JsonIgnoreProperties({"formulaList", "consultaList", "consultorioList"})
public class Paciente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_paciente")
    @JsonProperty("idPaciente")
    private Integer idPaciente;

    @NotEmpty(message = "El nombre del paciente no puede estar vacío")
    @Column(name = "nombre_paciente")
    @JsonProperty("nombrePaciente")
    private String nombrePaciente;

    @NotEmpty(message = "El apellido del paciente no puede estar vacío")
    @Column(name = "apellido_paciente")
    @JsonProperty("apellidoPaciente")
    private String apellidoPaciente;

    @NotEmpty(message = "La dirección del paciente no puede estar vacía")
    @Column(name = "direccion_paciente")
    @JsonProperty("direccionPaciente")
    private String direccionPaciente;

    @NotEmpty(message = "El teléfono del paciente no puede estar vacío")
    @Column(name = "telefono_paciente")
    @JsonProperty("telefonoPaciente")
    private String telefonoPaciente;

    @NotEmpty(message = "El email del paciente no puede estar vacío")
    @Email(message = "El email debe tener un formato válido")
    @Column(name = "email_paciente")
    @JsonProperty("emailPaciente")
    private String emailPaciente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    @JsonBackReference
    @JsonProperty("facturaList")
    private List<Factura> facturaList;

    @JoinColumn(name = "eps_id", referencedColumnName = "id_eps")
    @ManyToOne
    @JsonProperty("eps")
    private Eps eps;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    private List<Formula> formulaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    private List<Consulta> consultaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    private List<Consultorio> consultorioList;

    @Override
    public String toString() {
        return "Paciente{\n" +
                "idPaciente=" + idPaciente + ",\n" +
                "nombrePaciente='" + nombrePaciente + "',\n" +
                "apellidoPaciente='" + apellidoPaciente + "',\n" +
                "direccionPaciente='" + direccionPaciente + "',\n" +
                "telefonoPaciente='" + telefonoPaciente + "',\n" +
                "emailPaciente='" + emailPaciente + "',\n" +
                "eps=" + eps + "\n" +
                '}';
    }
}
