package cl.duoc.mineria.palas.mapper;

import cl.duoc.mineria.palas.dto.RegistrarPalaDTO;
import cl.duoc.mineria.palas.model.EstadoPala;
import cl.duoc.mineria.palas.model.Pala;
import org.springframework.stereotype.Component;

@Component
public class PalaMapper {

    public Pala toEntity(RegistrarPalaDTO dto) {
        if (dto == null) return null;

        Pala pala = new Pala();
        pala.setModelo(dto.getModelo());
        pala.setSector(dto.getSector());
        pala.setEstadoPala(EstadoPala.DISPONIBLE);
        return pala;
    }
}