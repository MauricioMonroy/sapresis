package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Dependencia;

import java.util.List;

public interface IDependenciaServicio {
    public List<Dependencia> listarDependencias();

    public Dependencia buscarDependenciaPorId(Integer idDependencia);

    public Dependencia guardarDependencia(Dependencia dependencia);

    public void eliminarDependencia(Dependencia dependencia);
}
