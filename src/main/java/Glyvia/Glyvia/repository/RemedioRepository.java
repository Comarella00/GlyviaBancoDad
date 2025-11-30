package Glyvia.Glyvia.repository;

import Glyvia.Glyvia.model.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {
    List<Remedio> findByUsuarioId(Long idUsuario);

}
