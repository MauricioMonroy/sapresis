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
        @NamedQuery(name = "Eps.findAll", query = "SELECT e FROM Eps e")})
public class Eps implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_eps")
    private Integer idEps;
    @Basic(optional = false)
    @Column(name = "nombre_eps")
    private String nombreEps;
    @Basic(optional = false)
    @Column(name = "telefono_eps")
    private String telefonoEps;
    @Basic(optional = false)
    @Column(name = "email_eps")
    private String emailEps;
    @OneToMany(mappedBy = "eps")
    private List<Paciente> pacienteList;
}
