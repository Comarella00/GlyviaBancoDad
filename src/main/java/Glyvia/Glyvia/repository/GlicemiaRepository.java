package Glyvia.Glyvia.repository;

import Glyvia.Glyvia.model.Glicemia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GlicemiaRepository extends JpaRepository<Glicemia, Long> {
    List<Glicemia> findByUsuarioIdOrderByDataGlicemiaDescHoraGlicemiaDesc(
            Long id); //Mais recentes primeiro
    Optional<Glicemia> findTopByUsuarioIdOrderByDataGlicemiaDescHoraGlicemiaDesc(
            Long idUsuario);
    List<Glicemia> findByUsuarioIdAndDataGlicemia(
            Long idUsuario, LocalDate data);
    List<Glicemia> findByUsuarioIdAndDataGlicemiaBetweenOrderByDataGlicemiaAscHoraGlicemiaAsc(
            Long idUsuario, LocalDate dataInicio, LocalDate dataFim);
}
