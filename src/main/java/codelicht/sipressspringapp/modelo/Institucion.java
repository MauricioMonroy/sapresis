package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Institucion implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_institucion")
    private Integer idInstitucion;
    @Column(name = "nombre_institucion")
    private String nombreInstitucion;
    @Column(name = "direccion_institucion")
    private String direccionInstitucion;
    @Column(name = "telefono_institucion")
    private String telefonoInstitucion;
    @Column(name = "codigo_postal")
    private String codigoPostal;
    @OneToMany(mappedBy = "institucion")
    @JsonManagedReference
    private List<Dependencia> dependenciaList;

}
