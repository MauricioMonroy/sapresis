package codelicht.sipressspringapp.servicio.implementacion;

import codelicht.sipressspringapp.modelo.Habitacion;
import codelicht.sipressspringapp.repositorio.HabitacionRepositorio;
import codelicht.sipressspringapp.servicio.interfaces.IHabitacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para la entidad Habitacion.
 */
@Service
public class HabitiacionServicio implements IHabitacionServicio {

    @Autowired
    private HabitacionRepositorio habitacionRepositorio;

    @Override
    public List<Habitacion> listarHabitaciones() {
        return habitacionRepositorio.findAll();
    }

    @Override
    public Habitacion buscarHabitacionPorId(Integer idHabitacion) {
        return habitacionRepositorio.findById(idHabitacion).orElse(null);
    }

    @Override
    public Habitacion guardarHabitacion(Habitacion habitacion) {
        return habitacionRepositorio.save(habitacion);
    }

    @Override
    public void eliminarHabitacion(Habitacion habitacion) {
        habitacionRepositorio.delete(habitacion);
    }
}
