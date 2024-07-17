package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Basic(optional = false)
    @Column(name = "nombre_eps")
    @JsonProperty("nombreEps")
    private String nombreEps;

    @Basic(optional = false)
    @Column(name = "telefono_eps")
    @JsonProperty("telefonoEps")
    private String telefonoEps;

    @Basic(optional = false)
    @Column(name = "email_eps")
    @JsonProperty("emailEps")
    private String emailEps;

    @OneToMany(mappedBy = "eps")
    private List<Paciente> pacienteList;

    @Override
    public String toString() {
        return "Eps -> {" +
                " ID de la EPS: '" + idEps + "'\n" +
                " Nombre de la EPS: '" + nombreEps + "'\n" +
                " Teléfono de la EPS: '" + telefonoEps + "'\n" +
                " Email de la EPS: '" + emailEps + "}" +
                '\n';
    }
}
