package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Personal;

import java.util.List;

public interface IPersonalServicio {
    public List<Personal> listarPersonalS();

    public Personal buscarPersonalPorId(Integer idPersonal);

    public Personal guardarPersonal(Personal personal);

    public void eliminarPersonal(Personal personal);
}
