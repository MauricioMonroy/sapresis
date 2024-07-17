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
        @NamedQuery(name = "Personal.findAll", query = "SELECT p FROM Personal p")})
@JsonIgnoreProperties({"consultorioList"})
public class Personal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_personal")
    @JsonProperty("idPersonal")
    private Integer idPersonal;

    @Column(name = "nombre_personal")
    @JsonProperty("nombrePersonal")
    private String nombrePersonal;

    @Column(name = "apellido_personal")
    @JsonProperty("apellidoPersonal")
    private String apellidoPersonal;

    @Column(name = "telefono_personal")
    @JsonProperty("telefonoPersonal")
    private String telefonoPersonal;

    @Column(name = "email_personal")
    @JsonProperty("emailPersonal")
    private String emailPersonal;

    @JoinColumn(name = "dependencia_id", referencedColumnName = "id_dependencia")
    @ManyToOne
    @JsonProperty("dependencia")
    private Dependencia dependencia;

    @OneToMany(mappedBy = "personal")
    private List<Consultorio> consultorioList;

    @Override
    public String toString() {
        return "Personal{" +
                "idPersonal=" + idPersonal +
                ", nombrePersonal='" + nombrePersonal + '\'' +
                ", apellidoPersonal='" + apellidoPersonal + '\'' +
                ", telefonoPersonal='" + telefonoPersonal + '\'' +
                ", emailPersonal='" + emailPersonal + '\'' +
                ", dependencia=" + dependencia +
                '}';
    }
}