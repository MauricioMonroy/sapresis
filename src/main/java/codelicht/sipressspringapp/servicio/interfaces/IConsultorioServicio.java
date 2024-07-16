package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Consultorio;

import java.util.List;

/**
 * Interface para la entidad Consultorio.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IConsultorioServicio {
    public List<Consultorio> listarConsultorios();

    public Consultorio buscarConsultorioPorId(Integer idConsultorio);

    public Consultorio guardarConsultorio(Consultorio consultorio);

    public void eliminarConsultorio(Consultorio consultorio);
}
