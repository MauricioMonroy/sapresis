package codelicht.sapresis.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d")})
@JsonIgnoreProperties({"consultaList"})
public class Doctor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_doctor")
    @JsonProperty("idDoctor")
    private Integer idDoctor;

    @NotEmpty(message = "El nombre del doctor no puede estar vacío")
    @Column(name = "nombre_doctor")
    @JsonProperty("nombreDoctor")
    private String nombreDoctor;

    @NotEmpty(message = "El apellido del doctor no puede estar vacío")
    @Column(name = "apellido_doctor")
    @JsonProperty("apellidoDoctor")
    private String apellidoDoctor;

    @NotEmpty(message = "El teléfono del doctor no puede estar vacío")
    @Column(name = "telefono_doctor")
    @JsonProperty("telefonoDoctor")
    private String telefonoDoctor;

    @NotEmpty(message = "El email del doctor no puede estar vacío")
    @Column(name = "email_doctor")
    @JsonProperty("emailDoctor")
    private String emailDoctor;

    @JoinColumn(name = "dependencia_id", referencedColumnName = "id_dependencia")
    @ManyToOne
    @JsonProperty("dependencia")
    private Dependencia dependencia;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<Consulta> consultaList;

    @Override
    public String toString() {
        return "Doctor{\n" +
                "idDoctor=" + idDoctor + ",\n" +
                "nombreDoctor='" + nombreDoctor + "',\n" +
                "apellidoDoctor='" + apellidoDoctor + "',\n" +
                "telefonoDoctor='" + telefonoDoctor + "',\n" +
                "emailDoctor='" + emailDoctor + "',\n" +
                "dependencia=" + dependencia + "\n" +
                '}';
    }
}

