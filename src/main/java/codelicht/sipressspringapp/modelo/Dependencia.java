package codelicht.sipressspringapp.modelo;

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
public class Dependencia implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_dependencia")
    private Integer idDependencia;
    @Column(name = "nombre_dependencia")
    private String nombreDependencia;
    @OneToMany(mappedBy = "dependencia")
    private List<Doctor> doctorList;
    @OneToMany(mappedBy = "dependencia")
    private List<Personal> personalList;
    @JoinColumn(name = "institucion_id", referencedColumnName = "id_institucion")
    @ManyToOne
    private Institucion institucion;
}
