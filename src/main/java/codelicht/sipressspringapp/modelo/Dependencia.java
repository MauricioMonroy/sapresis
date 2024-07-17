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

    @Column(name = "nombre_dependencia")
    @JsonProperty("nombreDependencia")
    private String nombreDependencia;

    @OneToMany(mappedBy = "dependencia")
    @JsonBackReference
    @JsonProperty("doctorList")
    private List<Doctor> doctorList;

    @OneToMany(mappedBy = "dependencia")
    @JsonBackReference
    @JsonProperty("personalList")
    private List<Personal> personalList;

    @JoinColumn(name = "institucion_id", referencedColumnName = "id_institucion")
    @ManyToOne
    @JsonManagedReference
    @JsonProperty("institucion")
    private Institucion institucion;

    @Override
    public String toString() {
        return "Dependencia{" +
                "idDependencia=" + idDependencia +
                ", nombreDependencia='" + nombreDependencia + '\'' +
                ", institucion=" + institucion +
                '}';
    }
}
