package Glyvia.Glyvia.repository;

import Glyvia.Glyvia.model.Refeicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
    List<Refeicao> findByUsuarioIdAndDataRefeicaoBetween(
            Long usuarioId,
            LocalDate inicio,
            LocalDate fim
    );
    List<Refeicao> findByUsuarioIdAndDataRefeicaoBetweenOrderByDataRefeicaoAscHoraRefeicaoAsc(
            Long idUsuario, LocalDate dataInicio, LocalDate dataFim);
}
