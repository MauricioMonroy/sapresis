package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Habitacion;

import java.util.List;

/**
 * Interface para la entidad Habitacion.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IHabitacionServicio {
    public List<Habitacion> listarHabitaciones();

    public Habitacion buscarHabitacionPorId(Integer idHabitacion);

    public Habitacion guardarHabitacion(Habitacion habitacion);

    public void eliminarHabitacion(Habitacion habitacion);
}
