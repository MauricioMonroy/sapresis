package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Historial;

import java.util.List;

public interface IHistorialServicio {
    List<Historial> listarRegistros();

    Historial buscarRegistroPorId(Integer idHistorial);

    Historial guardarRegistro(Historial historial);

    void eliminarRegistro(Historial historial);
}
