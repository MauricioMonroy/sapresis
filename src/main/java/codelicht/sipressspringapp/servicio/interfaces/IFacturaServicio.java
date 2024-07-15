package codelicht.sipressspringapp.servicio.interfaces;

import codelicht.sipressspringapp.modelo.Factura;

import java.util.List;

/**
 * Interface para la entidad Factura.
 * Contiene los métodos a implementar en operaciones CRUD básicas.
 */
public interface IFacturaServicio {
    public List<Factura> listarFacturas();

    public Factura buscarFacturaPorId(Integer idFactura);

    public Factura guardarFactura(Factura factura);

    public void eliminarFactura(Factura factura);
}
