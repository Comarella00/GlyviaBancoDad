package Glyvia.Glyvia.repository;

import Glyvia.Glyvia.model.Consulta;
import Glyvia.Glyvia.model.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemedioRepository extends JpaRepository<Remedio, Long> {
    List<Remedio> findByUsuario_Id(Long usuarioId);
    
    List<Consulta> findByConsultaUsuarioId(Long idUsuario);

    List<Remedio> findByRemedioUsuarioId(Long idUsuario);


}
