package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Eps;

import java.util.List;

public interface IEpsServicio {
    public List<Eps> listarEpsS();

    public Eps buscarEpsPorId(Integer idEps);

    public Eps guardarEps(Eps eps);

    public void eliminarEps(Eps eps);
}
