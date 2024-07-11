package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Historial;
import codelicht.sipressspringapp.repositorio.HistorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialServicio implements IHistorialServicio {

    @Autowired
    private HistorialRepositorio historialRepositorio;

    @Override
    public List<Historial> listarRegistros() {
        return historialRepositorio.findAll();
    }

    @Override
    public Historial buscarRegistroPorId(Integer idHistorial) {
        return historialRepositorio.findById(idHistorial).orElse(null);
    }

    @Override
    public Historial guardarRegistro(Historial historial) {
        return historialRepositorio.save(historial);
    }

    @Override
    public void eliminarRegistro(Historial historial) {
        historialRepositorio.delete(historial);
    }
}
