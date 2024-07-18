package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Factura;
import codelicht.sipressspringapp.modelo.Paciente;
import codelicht.sipressspringapp.servicio.interfaces.IFacturaServicio;
import codelicht.sipressspringapp.servicio.interfaces.IPacienteServicio;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class FacturaControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(FacturaControlador.class);

    @Autowired
    private IFacturaServicio facturaServicio;

    @Autowired
    private IPacienteServicio pacienteServicio;

    // http://localhost:8080/sipress-app/facturas
    @GetMapping("/facturas")
    public List<Factura> obtenerFacturas() {
        var facturas = facturaServicio.listarFacturas();
        facturas.forEach((factura -> logger.info(factura.toString())));
        return facturas;
    }

    @GetMapping("/facturas/{id}")
    public Factura buscarFacturaPorId(@PathVariable Integer id) {
        return facturaServicio.buscarFacturaPorId(id);
    }

    @PostMapping("/facturas")
    public ResponseEntity<?> agregarFactura(@Valid @RequestBody Factura factura, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        logger.info("Factura a agregar: " + factura);
        if (factura.getPaciente() != null) {
            Paciente paciente = pacienteServicio.buscarPacientePorId(factura.getPaciente().getIdPaciente());
            factura.setPaciente(paciente);
        }
        Factura nuevaFactura = facturaServicio.guardarFactura(factura);
        return ResponseEntity.ok(nuevaFactura);
    }

    @DeleteMapping("/facturas/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable("id") Integer id) {
        Factura factura = facturaServicio.buscarFacturaPorId(id);
        if (factura != null) {
            facturaServicio.eliminarFactura(factura);
            return ResponseEntity.noContent().build(); // Elimina y responde con 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Responde con 404 Not Found si no existe
        }
    }
}
