package Glyvia.Glyvia.repository;

import Glyvia.Glyvia.model.Glicemia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlicemiaRepository extends JpaRepository<Glicemia, Long> {
    List<Glicemia> findByUsuarioIdOrderByDataGlicemiaDescHoraGlicemiaDesc(Long id); //Mais recentes primeiro

}
