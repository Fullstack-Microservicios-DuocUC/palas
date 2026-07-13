package cl.duoc.mineria.palas.service;

import cl.duoc.mineria.palas.dto.ActualizarEstadoPalaDTO;
import cl.duoc.mineria.palas.dto.RegistrarPalaDTO;
import cl.duoc.mineria.palas.exception.PalaNotFoundException;
import cl.duoc.mineria.palas.mapper.PalaMapper;
import cl.duoc.mineria.palas.model.Pala;
import cl.duoc.mineria.palas.repository.PalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PalaService {

    private final PalaRepository palaRepository;
    private final PalaMapper palaMapper;

    // ==========================================================
    // REGISTRAR PALA
    // ==========================================================
    @Transactional
    public Pala registrarPala(RegistrarPalaDTO dto) {

        // Normaliza el modelo eliminando espacios repetidos
        // y convirtiéndolo a mayúsculas.
        dto.setModelo(
                dto.getModelo()
                        .trim()
                        .replaceAll("\\s+", " ")
                        .toUpperCase()
        );

        // Normaliza el sector eliminando espacios repetidos.
        dto.setSector(
                dto.getSector()
                        .trim()
                        .replaceAll("\\s+", " ")
        );

        // Convierte el DTO en una entidad
        Pala nuevaPala = palaMapper.toEntity(dto);

        // Guarda la pala
        return palaRepository.save(nuevaPala);
    }

    // ==========================================================
    // LISTAR TODAS LAS PALAS
    // ==========================================================
    @Transactional(readOnly = true)
    public List<Pala> listarTodas() {

        return palaRepository.findAll();
    }

    // ==========================================================
    // BUSCAR PALA POR ID
    // ==========================================================
    @Transactional(readOnly = true)
    public Pala obtenerPorId(Long id) {

        return palaRepository.findById(id)
                .orElseThrow(() ->
                        new PalaNotFoundException(
                                "La pala con ID "
                                        + id
                                        + " no se encuentra registrada en la faena."));
    }

    // ==========================================================
    // ACTUALIZAR ESTADO DE LA PALA
    // ==========================================================
    @Transactional
    public Pala actualizarEstado(Long id, ActualizarEstadoPalaDTO dto) {

        Pala pala = obtenerPorId(id);

        // Evita actualizar al mismo estado.
        if (pala.getEstadoPala() == dto.getEstadoPala()) {

            throw new IllegalArgumentException(
                    "La pala ya posee el estado "
                            + dto.getEstadoPala());
        }

        // Actualiza el estado.
        pala.setEstadoPala(dto.getEstadoPala());

        return palaRepository.save(pala);
    }

    // ==========================================================
    // VALIDAR EXISTENCIA DE LA PALA
    // ==========================================================
    @Transactional(readOnly = true)
    public boolean existePala(Long id) {

        return palaRepository.existsById(id);
    }

}
