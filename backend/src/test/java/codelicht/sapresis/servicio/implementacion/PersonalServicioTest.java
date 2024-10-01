package codelicht.sapresis.servicio.implementacion;

import codelicht.sapresis.modelo.Personal;
import codelicht.sapresis.repositorio.PersonalRepositorio;
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
 * Clase de pruebas para el servicio de Personal.
 * Utiliza Mockito para simular el repositorio de Personal.
 * Se prueban los métodos CRUD del servicio.
 */

class PersonalServicioTest {

    @Mock
    private PersonalRepositorio personalRepositorio;  // Simula el repositorio de Personal

    @InjectMocks
    private PersonalServicio personalServicio;  // Servicio a probar

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa las anotaciones de Mockito
    }

    // Prueba para recuperar la lista de registros de Personal
    @Test
    public void testListarPersonales() {
        Personal personal1 = new Personal();
        Personal personal2 = new Personal();
        when(personalRepositorio.findAll()).thenReturn(Arrays.asList(personal1, personal2));

        List<Personal> personales = personalServicio.listarPersonalS();

        assertEquals(2, personales.size());
        verify(personalRepositorio, times(1)).findAll();
    }

    // Prueba para buscar un Personal por su ID
    @Test
    public void testBuscarPersonalPorId() {
        Personal personal = new Personal();
        when(personalRepositorio.findById(1)).thenReturn(Optional.of(personal));

        Personal resultado = personalServicio.buscarPersonalPorId(1);

        assertNotNull(resultado);
        verify(personalRepositorio, times(1)).findById(1);
    }

    // Prueba para guardar un Personal
    @Test
    public void testGuardarPersonal() {
        Personal personal = new Personal();
        when(personalRepositorio.save(personal)).thenReturn(personal);

        Personal resultado = personalServicio.guardarPersonal(personal);

        assertEquals(personal, resultado);
        verify(personalRepositorio, times(1)).save(personal);
    }

    // Prueba para eliminar un Personal
    @Test
    public void testEliminarPersonal() {
        Personal personal = new Personal();

        personalServicio.eliminarPersonal(personal);

        verify(personalRepositorio, times(1)).delete(personal);
    }
}