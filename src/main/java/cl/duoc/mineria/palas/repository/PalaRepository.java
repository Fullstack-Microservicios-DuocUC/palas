package cl.duoc.mineria.palas.repository;

import cl.duoc.mineria.palas.model.Pala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalaRepository extends JpaRepository<Pala, Long> {
}