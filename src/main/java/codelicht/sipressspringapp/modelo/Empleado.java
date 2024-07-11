package codelicht.sipressspringapp.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @Column(name = "id_empleado", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @Column(name = "cargo", length = 45)
    private String cargo;

    @Column(name = "sueldo")
    private Double sueldo;

}