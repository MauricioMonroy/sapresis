package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Consulta;

import java.util.List;

/**
 * Interface para la entidad Consulta.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IConsultaServicio {
    public List<Consulta> listarConsultas();

    public Consulta buscarConsultaPorId(Integer idConsulta);

    public Consulta guardarConsulta(Consulta consulta);

    public void eliminarConsulta(Consulta consulta);
}
