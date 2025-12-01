package Glyvia.Glyvia.service;

import Glyvia.Glyvia.dto.ConsultaRequest;
import Glyvia.Glyvia.model.Consulta;
import Glyvia.Glyvia.model.Usuario;
import Glyvia.Glyvia.repository.ConsultaRepository;
import Glyvia.Glyvia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Consulta adicionarConsulta(ConsultaRequest dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setDescricao(dto.getDescricao());
        consulta.setData(LocalDate.parse(dto.getData()));
        consulta.setHorario(LocalTime.parse(dto.getHorario()));
        consulta.setUsuario(usuario);

        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarPorUsuario(Long idUsuario) {
        return consultaRepository.findByUsuarioId(idUsuario);
    }
}
