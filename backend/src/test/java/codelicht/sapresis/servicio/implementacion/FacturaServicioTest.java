package codelicht.sapresis.servicio.implementacion;

import codelicht.sapresis.modelo.Factura;
import codelicht.sapresis.repositorio.FacturaRepositorio;
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
 * Clase de pruebas para el servicio de Factura.
 * Utiliza Mockito para simular el repositorio de Factura.
 * Se prueban los métodos CRUD del servicio.
 */

class FacturaServicioTest {

    @Mock
    private FacturaRepositorio facturaRepositorio;  // Simula el repositorio de Factura

    @InjectMocks
    private FacturaServicio facturaServicio;  // Servicio a probar

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa las anotaciones de Mockito
    }

    // Prueba para recuperar la lista de registros de Factura
    @Test
    public void testListarFacturas() {
        Factura factura1 = new Factura();
        Factura factura2 = new Factura();
        when(facturaRepositorio.findAll()).thenReturn(Arrays.asList(factura1, factura2));

        List<Factura> facturas = facturaServicio.listarFacturas();

        assertEquals(2, facturas.size());
        verify(facturaRepositorio, times(1)).findAll();
    }

    // Prueba para buscar una Factura por su ID
    @Test
    public void testBuscarFacturaPorId() {
        Factura factura = new Factura();
        when(facturaRepositorio.findById(1)).thenReturn(Optional.of(factura));

        Factura resultado = facturaServicio.buscarFacturaPorId(1);

        assertNotNull(resultado);
        verify(facturaRepositorio, times(1)).findById(1);
    }

    // Prueba para guardar una Factura
    @Test
    public void testGuardarFactura() {
        Factura factura = new Factura();
        when(facturaRepositorio.save(factura)).thenReturn(factura);

        Factura resultado = facturaServicio.guardarFactura(factura);

        assertEquals(factura, resultado);
        verify(facturaRepositorio, times(1)).save(factura);
    }

    // Prueba para eliminar una Factura
    @Test
    public void testEliminarFactura() {
        Factura factura = new Factura();

        facturaServicio.eliminarFactura(factura);

        verify(facturaRepositorio, times(1)).delete(factura);
    }
}