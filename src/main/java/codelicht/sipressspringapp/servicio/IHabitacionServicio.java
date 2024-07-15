package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Habitacion;

import java.util.List;

public interface IHabitacionServicio {
    public List<Habitacion> listarHabitaciones();

    public Habitacion buscarHabitacionPorId(Integer idHabitacion);

    public Habitacion guardarHabitacion(Habitacion habitacion);

    public void eliminarHabitacion(Habitacion habitacion);
}
