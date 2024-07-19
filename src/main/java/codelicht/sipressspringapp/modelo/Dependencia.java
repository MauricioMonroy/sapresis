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
        @NamedQuery(name = "Dependencia.findAll", query = "SELECT d FROM Dependencia d")})
@JsonIgnoreProperties({"doctorList", "personalList"})
public class Dependencia implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_dependencia")
    @JsonProperty("idDependencia")
    private Integer idDependencia;

    @NotEmpty(message = "El nombre de la dependencia no puede estar vacío")
    @Column(name = "nombre_dependencia")
    @JsonProperty("nombreDependencia")
    private String nombreDependencia;

    @OneToMany(mappedBy = "dependencia")
    private List<Doctor> doctorList;

    @OneToMany(mappedBy = "dependencia")
    private List<Personal> personalList;

    @JoinColumn(name = "institucion_id", referencedColumnName = "id_institucion")
    @ManyToOne
    @JsonProperty("institucion")
    private Institucion institucion;

    @Override
    public String toString() {
        return "Dependencia{\n" +
                "idDependencia=" + idDependencia + ",\n" +
                "nombreDependencia='" + nombreDependencia + "',\n" +
                "institucion=" + institucion + "\n" +
                '}';
    }
}
