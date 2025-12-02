package Glyvia.Glyvia.repository;

import Glyvia.Glyvia.model.Refeicao;
import Glyvia.Glyvia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
    List<Refeicao> findByUsuario(Usuario usuario);

    List<Refeicao> findByUsuarioIdAndDataRefeicaoBetween(Long usuarioId, LocalDate inicio, LocalDate fim);

    List<Refeicao> findByUsuarioIdAndDataRefeicaoBetweenOrderByDataRefeicaoAscHoraRefeicaoAsc(
            Long idUsuario,
            LocalDate inicio,
            LocalDate fim
    );

    List<Refeicao> findByUsuarioIdOrderByDataRefeicaoDescHoraRefeicaoDesc(Long idUsuario);

}
