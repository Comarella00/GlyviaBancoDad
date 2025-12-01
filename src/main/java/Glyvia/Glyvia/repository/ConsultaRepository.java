package Glyvia.Glyvia.repository;

import Glyvia.Glyvia.model.Consulta;
import Glyvia.Glyvia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByUsuarioId(Long idUsuario);

}