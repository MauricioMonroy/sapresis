package codelicht.sipresswebapp.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
        @NamedQuery(name = "Eps.findAll", query = "SELECT e FROM Eps e")})
@JsonIgnoreProperties({"pacienteList"})
public class Eps implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_eps")
    @JsonProperty("idEps")
    private Integer idEps;

    @NotEmpty(message = "El nombre de la EPS no puede estar vacío")
    @Column(name = "nombre_eps")
    @JsonProperty("nombreEps")
    private String nombreEps;

    @NotEmpty(message = "El teléfono de la EPS no puede estar vacío")
    @Column(name = "telefono_eps")
    @JsonProperty("telefonoEps")
    private String telefonoEps;

    @NotEmpty(message = "El email de la EPS no puede estar vacío")
    @Column(name = "email_eps")
    @JsonProperty("emailEps")
    private String emailEps;

    @OneToMany(mappedBy = "eps")
    private List<Paciente> pacienteList;

    @Override
    public String toString() {
        return "Eps{\n" +
                "idEps=" + idEps + ",\n" +
                "nombreEps='" + nombreEps + "',\n" +
                "telefonoEps='" + telefonoEps + "',\n" +
                "emailEps='" + emailEps + "'\n" +
                '}';
    }
}
