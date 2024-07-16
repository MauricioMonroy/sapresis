package codelicht.sipressspringapp.modelo;

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

    @Column(name = "nombre_institucion")
    @JsonProperty("nombreInstitucion")
    private String nombreInstitucion;

    @Column(name = "direccion_institucion")
    @JsonProperty("direccionInstitucion")
    private String direccionInstitucion;

    @Column(name = "telefono_institucion")
    @JsonProperty("telefonoInstitucion")
    private String telefonoInstitucion;

    @Column(name = "codigo_postal")
    @JsonProperty("codigoPostal")
    private String codigoPostal;

    @OneToMany(mappedBy = "institucion")
    @JsonManagedReference
    @JsonProperty("dependenciaList")
    private List<Dependencia> dependenciaList;

    @Override
    public String toString() {
        return "Institucion{" +
                "idInstitucion=" + idInstitucion +
                ", nombreInstitucion='" + nombreInstitucion + '\'' +
                '}';
    }

}
