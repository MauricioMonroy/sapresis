package codelicht.sipressspringapp.modelo;

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
        @NamedQuery(name = "Institucion.findAll", query = "SELECT i FROM Institucion i")})
@JsonIgnoreProperties({"dependenciaList"})
public class Institucion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_institucion")
    @JsonProperty("idInstitucion")
    private Integer idInstitucion;

    @NotEmpty(message = "El nombre de la institución no puede estar vacío")
    @Column(name = "nombre_institucion")
    @JsonProperty("nombreInstitucion")
    private String nombreInstitucion;

    @NotEmpty(message = "La dirección de la institución no puede estar vacía")
    @Column(name = "direccion_institucion")
    @JsonProperty("direccionInstitucion")
    private String direccionInstitucion;

    @NotEmpty(message = "El teléfono de la institución no puede estar vacío")
    @Column(name = "telefono_institucion")
    @JsonProperty("telefonoInstitucion")
    private String telefonoInstitucion;

    @NotEmpty(message = "El código postal de la institución no puede estar vacío")
    @Column(name = "codigo_postal")
    @JsonProperty("codigoPostal")
    private String codigoPostal;

    @OneToMany(mappedBy = "institucion")
    private List<Dependencia> dependenciaList;

    @Override
    public String toString() {
        return "Institucion -> {" +
                " ID de la Institución: '" + idInstitucion + "'\n" +
                " Nombre de la Institución: '" + nombreInstitucion + "'\n" +
                " Dirección de la Institución: '" + direccionInstitucion + "'\n" +
                " Teléfono de la Institución: '" + telefonoInstitucion + "'\n" +
                " Código Postal: '" + codigoPostal + "}" +
                '\n';
    }
}
