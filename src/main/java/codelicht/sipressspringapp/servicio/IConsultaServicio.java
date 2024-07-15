package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Consulta;

import java.util.List;

public interface IConsultaServicio {
    public List<Consulta> listarConsultas();

    public Consulta buscarConsultaPorId(Integer idConsulta);

    public Consulta guardarConsulta(Consulta consulta);

    public void eliminarConsulta(Consulta consulta);
}
