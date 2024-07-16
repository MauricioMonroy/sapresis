package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.modelo.Factura;
import codelicht.sipressspringapp.servicio.interfaces.IFacturaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sipress-app")
@CrossOrigin(value = "http://localhost:3000")
public class FacturaControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(FacturaControlador.class);

    @Autowired
    private IFacturaServicio facturaServicio;

    // http://localhost:8080/sipress-app/facturas
    @GetMapping("/facturas")
    public List<Factura> obtenerFacturas() {
        var facturas = facturaServicio.listarFacturas();
        facturas.forEach((factura -> logger.info(factura.toString())));
        return facturas;
    }

    @PostMapping("/facturas")
    public Factura agregarFactura(@RequestBody Factura factura) {
        logger.info("Factura a agregar: " + factura);
        return facturaServicio.guardarFactura(factura);
    }
}
