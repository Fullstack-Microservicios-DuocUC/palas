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

    @Transactional
    public Pala registrarPala(RegistrarPalaDTO dto) {
        Pala nuevaPala = palaMapper.toEntity(dto);
        return palaRepository.save(nuevaPala);
    }

    @Transactional(readOnly = true)
    public List<Pala> listarTodas() {
        return palaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pala obtenerPorId(Long id) {
        return palaRepository.findById(id)
                .orElseThrow(() -> new PalaNotFoundException("La pala con ID " + id + " no se encuentra en la faena."));
    }

    @Transactional
    public Pala actualizarEstado(Long id, ActualizarEstadoPalaDTO dto) {
        Pala pala = obtenerPorId(id);
        pala.setEstadoPala(dto.getEstadoPala());
        return palaRepository.save(pala);
    }

    @Transactional(readOnly = true)
    public boolean existePala(Long id) {
        return palaRepository.existsById(id);
    }
}
