package codelicht.sapresis.controlador;

import codelicht.sapresis.excepcion.RecursoNoEncontradoExcepcion;
import codelicht.sapresis.modelo.Factura;
import codelicht.sapresis.modelo.Paciente;
import codelicht.sapresis.servicio.interfaces.IFacturaServicio;
import codelicht.sapresis.servicio.interfaces.IPacienteServicio;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sapresis")
@CrossOrigin(value = "http://localhost:3000")
public class FacturaControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(FacturaControlador.class);

    private final IFacturaServicio facturaServicio;
    private final IPacienteServicio pacienteServicio;

    // Constructor para inyección de dependencias
    public FacturaControlador(IFacturaServicio facturaServicio, IPacienteServicio pacienteServicio) {
        this.facturaServicio = facturaServicio;
        this.pacienteServicio = pacienteServicio;
    }

    // http://localhost:8080/sapresis/facturas
    @GetMapping("/facturas")
    public List<Factura> obtenerFacturas() {
        var facturas = facturaServicio.listarFacturas();
        facturas.forEach((factura -> logger.info(factura.toString())));
        return facturas;
    }

    @GetMapping("/facturas/{id}")
    public ResponseEntity<Factura> buscarFacturaPorId(@PathVariable Integer id) {
        Factura factura = facturaServicio.buscarFacturaPorId(id);
        if (factura == null)
            throw new RecursoNoEncontradoExcepcion("Factura no encontrada con el número: " + id);
        return ResponseEntity.ok(factura);
    }

    @PostMapping("/facturas")
    public ResponseEntity<?> agregarFactura(@Valid @RequestBody Factura factura, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Factura a agregar: {}", factura);
        if (factura.getPaciente() != null) {
            Paciente paciente = pacienteServicio.buscarPacientePorId(factura.getPaciente().getIdPaciente());
            factura.setPaciente(paciente);
        }
        Factura nuevaFactura = facturaServicio.guardarFactura(factura);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevaFactura.getNumeroFactura())
                .toUri();
        return ResponseEntity.created(location).body(nuevaFactura);
    }

    @PutMapping("/facturas/{id}")
    public ResponseEntity<Factura> actualizarFactura(@PathVariable Integer id,
                                                     @RequestBody Factura facturaRecuperada) {
        Factura factura = facturaServicio.buscarFacturaPorId(id);
        if (factura == null)
            throw new RecursoNoEncontradoExcepcion("Factura no encontrada con el número: " + id);
        factura.setNumeroFactura(facturaRecuperada.getNumeroFactura());
        factura.setPaciente(facturaRecuperada.getPaciente());
        factura.setDescripcionServicio(facturaRecuperada.getDescripcionServicio());
        factura.setValor(facturaRecuperada.getValor());
        factura.setTotal(facturaRecuperada.getTotal());
        facturaServicio.guardarFactura(factura);
        return ResponseEntity.ok(factura);
    }

    @DeleteMapping("/facturas/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarFactura(@PathVariable Integer id) {
        Factura factura = facturaServicio.buscarFacturaPorId(id);
        if (factura == null)
            throw new RecursoNoEncontradoExcepcion("Factura no encontrada con el número: " + id);
        facturaServicio.eliminarFactura(factura);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
