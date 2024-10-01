package codelicht.sapresis.servicio.implementacion;

import codelicht.sapresis.modelo.Doctor;
import codelicht.sapresis.repositorio.DoctorRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Clase de pruebas para el servicio de Doctor.
 * Utiliza Mockito para simular el repositorio de Doctor.
 * Se prueban los métodos CRUD del servicio.
 */

public class DoctorServicioTest {

    @Mock
    private DoctorRepositorio doctorRepositorio;  // Simula el repositorio de Doctor

    @InjectMocks
    private DoctorServicio doctorServicio;  // Servicio a probar

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa las anotaciones de Mockito
    }

    // Prueba para recuperar la lista de registros de Doctor
    @Test
    public void testListarDoctores() {
        Doctor doctor1 = new Doctor();
        Doctor doctor2 = new Doctor();
        when(doctorRepositorio.findAll()).thenReturn(Arrays.asList(doctor1, doctor2));

        List<Doctor> doctores = doctorServicio.listarDoctores();

        assertEquals(2, doctores.size());
        verify(doctorRepositorio, times(1)).findAll();
    }

    // Prueba para buscar un Doctor por su ID
    @Test
    public void testBuscarDoctorPorId() {
        Doctor doctor = new Doctor();
        when(doctorRepositorio.findById(1)).thenReturn(Optional.of(doctor));

        Doctor resultado = doctorServicio.buscarDoctorPorId(1);

        assertNotNull(resultado);
        verify(doctorRepositorio, times(1)).findById(1);
    }

    // Prueba para guardar un Doctor
    @Test
    public void testGuardarDoctor() {
        Doctor doctor = new Doctor();
        when(doctorRepositorio.save(doctor)).thenReturn(doctor);

        Doctor resultado = doctorServicio.guardarDoctor(doctor);

        assertEquals(doctor, resultado);
        verify(doctorRepositorio, times(1)).save(doctor);
    }

    // Prueba para eliminar un Doctor
    @Test
    public void testEliminarDoctor() {
        Doctor doctor = new Doctor();

        doctorServicio.eliminarDoctor(doctor);

        verify(doctorRepositorio, times(1)).delete(doctor);
    }
}
