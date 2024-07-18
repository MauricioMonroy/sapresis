package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "\\d+", message = "El ID del personal debe ser un número")
    private Integer idPersonal;

    @NotEmpty(message = "El nombre del personal no puede estar vacío")
    @Column(name = "nombre_personal")
    @JsonProperty("nombrePersonal")
    private String nombrePersonal;

    @NotEmpty(message = "El apellido del personal no puede estar vacío")
    @Column(name = "apellido_personal")
    @JsonProperty("apellidoPersonal")
    private String apellidoPersonal;

    @NotEmpty(message = "El teléfono del personal no puede estar vacío")
    @Column(name = "telefono_personal")
    @JsonProperty("telefonoPersonal")
    private String telefonoPersonal;

    @NotEmpty(message = "El email del personal no puede estar vacío")
    @Email(message = "El email debe tener un formato válido")
    @Column(name = "email_personal")
    @JsonProperty("emailPersonal")
    private String emailPersonal;

    @JoinColumn(name = "dependencia_id", referencedColumnName = "id_dependencia")
    @ManyToOne
    @NotNull(message = "El campo de ID Dependencia no puede estar vacío")
    @JsonProperty("dependencia")
    private Dependencia dependencia;

    @OneToMany(mappedBy = "personal")
    private List<Consultorio> consultorioList;

    @Override
    public String toString() {
        return "Personal -> {" +
                " ID del Personal: '" + idPersonal + "'\n" +
                " Nombre: '" + nombrePersonal + "'\n" +
                " Apellido: '" + apellidoPersonal + "'\n" +
                " Teléfono: '" + telefonoPersonal + "'\n" +
                " Email: '" + emailPersonal + '}' + "'\n" +
                " | Dependencia -> {" + dependencia + "}" +
                '\n';
    }
}