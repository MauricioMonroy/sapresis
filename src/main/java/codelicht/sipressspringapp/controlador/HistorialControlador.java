package codelicht.sipressspringapp.controlador;

import codelicht.sipressspringapp.dto.HistorialDTO;
import codelicht.sipressspringapp.modelo.Historial;
import codelicht.sipressspringapp.servicio.IHistorialServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sipress-spring-app/historiales")
@CrossOrigin(value = "http://localhost:3000")
public class HistorialControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(HistorialControlador.class);

    @Autowired
    private IHistorialServicio historialServicio;

    // http://localhost:8080/sipress-spring-app/historiales
    @GetMapping()
    public List<HistorialDTO> listarHistoriales() {
        List<Historial> historiales = historialServicio.listarRegistros();
        historiales.forEach((historial -> logger.info(historial.toString())));
        return historiales.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    private HistorialDTO convertirADTO(Historial historial) {
        HistorialDTO dto = new HistorialDTO();
        dto.setId(historial.getId());
        dto.setMotivoConsulta(historial.getMotivoConsulta());
        dto.setFechaNacimiento(historial.getFechaNacimiento());
        dto.setSexo(historial.getSexo());
        dto.setDireccion(historial.getDireccion());
        dto.setOcupacion(historial.getOcupacion());
        dto.setContactoEmergencia(historial.getContactoEmergencia());
        dto.setNombreContactoEmergencia(historial.getNombreContactoEmergencia());
        dto.setAlergias(historial.getAlergias());
        dto.setCondicionesPreexistentes(historial.getCondicionesPreexistentes());
        dto.setMedicamentosActuales(historial.getMedicamentosActuales());
        dto.setHistorialVacunas(historial.getHistorialVacunas());
        dto.setGrupoSanguineo(historial.getGrupoSanguineo());
        dto.setNotasAdicionales(historial.getNotasAdicionales());
        dto.setUltimaActualizacion(historial.getUltimaActualizacion());
        return dto;
    }

}
