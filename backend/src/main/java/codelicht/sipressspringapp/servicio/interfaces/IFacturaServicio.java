package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Factura;

import java.util.List;

/**
 * Interface para la entidad Factura.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IFacturaServicio {
    List<Factura> listarFacturas();

    Factura buscarFacturaPorId(Integer idFactura);

    Factura guardarFactura(Factura factura);

    void eliminarFactura(Factura factura);
}
