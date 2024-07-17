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

    @Column(name = "nombre_doctor")
    @JsonProperty("nombreDoctor")
    private String nombreDoctor;

    @Column(name = "apellido_doctor")
    @JsonProperty("apellidoDoctor")
    private String apellidoDoctor;

    @Column(name = "telefono_doctor")
    @JsonProperty("telefonoDoctor")
    private String telefonoDoctor;

    @Column(name = "email_doctor")
    @JsonProperty("emailDoctor")
    private String emailDoctor;

    @JoinColumn(name = "dependencia_id", referencedColumnName = "id_dependencia")
    @ManyToOne
    @JsonManagedReference
    @JsonProperty("dependencia")
    private Dependencia dependencia;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    @JsonBackReference
    @JsonProperty("consultaList")
    private List<Consulta> consultaList;

    @Override
    public String toString() {
        return "Doctor{" +
                "idDoctor=" + idDoctor +
                ", nombreDoctor='" + nombreDoctor + '\'' +
                ", apellidoDoctor='" + apellidoDoctor + '\'' +
                ", telefonoDoctor='" + telefonoDoctor + '\'' +
                ", emailDoctor='" + emailDoctor + '\'' +
                ", dependencia=" + dependencia +
                '}';
    }
}

