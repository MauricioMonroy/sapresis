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
        @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d")})
public class Doctor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_doctor")
    private Integer idDoctor;
    @Column(name = "nombre_doctor")
    private String nombreDoctor;
    @Column(name = "apellido_doctor")
    private String apellidoDoctor;
    @Column(name = "telefono_doctor")
    private String telefonoDoctor;
    @Column(name = "email_doctor")
    private String emailDoctor;
    @JoinColumn(name = "dependencia_id", referencedColumnName = "id_dependencia")
    @ManyToOne
    private Dependencia dependencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<Consulta> consultaList;
}
