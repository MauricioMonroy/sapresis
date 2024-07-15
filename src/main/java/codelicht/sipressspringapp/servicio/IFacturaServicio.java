package codelicht.sipressspringapp.servicio;

import codelicht.sipressspringapp.modelo.Factura;

import java.util.List;

public interface IFacturaServicio {
    public List<Factura> listarFacturas();

    public Factura buscarFacturaPorId(Integer idFactura);

    public Factura guardarFactura(Factura factura);

    public void eliminarFactura(Factura factura);
}
