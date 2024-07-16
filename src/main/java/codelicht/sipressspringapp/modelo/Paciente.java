package codelicht.sipressspringapp.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
        @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p")})
public class Paciente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_paciente")
    private Integer idPaciente;
    @Column(name = "nombre_paciente")
    private String nombrePaciente;
    @Column(name = "apellido_paciente")
    private String apellidoPaciente;
    @Column(name = "direccion_paciente")
    private String direccionPaciente;
    @Column(name = "telefono_paciente")
    private String telefonoPaciente;
    @Column(name = "email_paciente")
    private String emailPaciente;
    @OneToMany(mappedBy = "paciente")
    @JsonManagedReference
    private List<Factura> facturaList;
    @JoinColumn(name = "eps_id", referencedColumnName = "id_eps")
    @ManyToOne
    @JsonBackReference
    private Eps eps;
    @OneToMany(mappedBy = "paciente")
    @JsonManagedReference
    private List<Formula> formulaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    @JsonManagedReference
    private List<Consulta> consultaList;
    @OneToMany(mappedBy = "paciente")
    @JsonManagedReference
    private List<Consultorio> consultorioList;

}