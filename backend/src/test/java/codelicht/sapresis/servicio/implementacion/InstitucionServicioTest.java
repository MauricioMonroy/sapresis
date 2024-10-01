package codelicht.sapresis.servicio.implementacion;

import codelicht.sapresis.modelo.Institucion;
import codelicht.sapresis.repositorio.InstitucionRepositorio;
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
 * Clase de pruebas para el servicio de Institucion.
 * Utiliza Mockito para simular el repositorio de Institucion.
 * Se prueban los métodos CRUD del servicio.
 */

class InstitucionServicioTest {

    @Mock
    private InstitucionRepositorio institucionRepositorio;  // Simula el repositorio de Institucion

    @InjectMocks
    private InstitucionServicio institucionServicio;  // Servicio a probar

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa las anotaciones de Mockito
    }

    // Prueba para recuperar la lista de registros de Institucion
    @Test
    public void testListarInstituciones() {
        Institucion institucion1 = new Institucion();
        Institucion institucion2 = new Institucion();
        when(institucionRepositorio.findAll()).thenReturn(Arrays.asList(institucion1, institucion2));

        List<Institucion> instituciones = institucionServicio.listarInstituciones();

        assertEquals(2, instituciones.size());
        verify(institucionRepositorio, times(1)).findAll();
    }

    // Prueba para buscar un Institucion por su ID
    @Test
    public void testBuscarInstitucionPorId() {
        Institucion institucion = new Institucion();
        when(institucionRepositorio.findById(1)).thenReturn(Optional.of(institucion));

        Institucion resultado = institucionServicio.buscarInstitucionPorId(1);

        assertNotNull(resultado);
        verify(institucionRepositorio, times(1)).findById(1);
    }

    // Prueba para guardar un Institucion
    @Test
    public void testGuardarInstitucion() {
        Institucion institucion = new Institucion();
        when(institucionRepositorio.save(institucion)).thenReturn(institucion);

        Institucion resultado = institucionServicio.guardarInstitucion(institucion);

        assertEquals(institucion, resultado);
        verify(institucionRepositorio, times(1)).save(institucion);
    }

    // Prueba para eliminar un Institucion
    @Test
    public void testEliminarInstitucion() {
        Institucion institucion = new Institucion();

        institucionServicio.eliminarInstitucion(institucion);

        verify(institucionRepositorio, times(1)).delete(institucion);
    }
}