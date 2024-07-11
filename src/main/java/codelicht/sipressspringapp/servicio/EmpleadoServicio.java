package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Empleado;
import codelicht.sipressspringapp.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServicio implements IEmpleadoServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    @Override
    public List<Empleado> listarRegistros() {
        return empleadoRepositorio.findAll();
    }

    @Override
    public Empleado buscarRegistroPorId(Integer idEmpleado) {
        return empleadoRepositorio.findById(idEmpleado).orElse(null);
    }

    @Override
    public Empleado guardarRegistro(Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    @Override
    public void eliminarRegistro(Empleado empleado) {
        empleadoRepositorio.delete(empleado);
    }
}
