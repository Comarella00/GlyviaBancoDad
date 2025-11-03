package Glyvia.Glyvia.service;

import Glyvia.Glyvia.dto.CadastroGlicemiaRequest;
import Glyvia.Glyvia.dto.HistoricoGlicemiaResponse;
import Glyvia.Glyvia.model.Glicemia;
import Glyvia.Glyvia.model.Usuario;
import Glyvia.Glyvia.repository.GlicemiaRepository;
import Glyvia.Glyvia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GlicemiaService {
    @Autowired
    private GlicemiaRepository glicemiaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Adiciona a glicemia
    public Glicemia cadastroGlicemia (CadastroGlicemiaRequest request){
        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Glicemia glicemia = new Glicemia();
        glicemia.setValorGlicemia(request.getValorGlicemia());
        glicemia.setDataGlicemia(request.getDataGlicemia());
        glicemia.setHoraGlicemia(request.getHoraGlicemia());
        glicemia.setUsuario(usuario); // 🔗 associa o usuário

        return glicemiaRepository.save(glicemia);
    }

    //Histórico recente da aba de adicionar glicemia
    public List<HistoricoGlicemiaResponse> historicoRecente(Long idUsuario) {
        List<Glicemia> glicemias = glicemiaRepository.findByUsuarioIdOrderByDataGlicemiaDescHoraGlicemiaDesc(idUsuario);

        return glicemias.stream()
                .map(g -> new HistoricoGlicemiaResponse(
                        g.getValorGlicemia(),
                        g.getDataGlicemia(),
                        g.getHoraGlicemia()))
                .collect(Collectors.toList());
    }
}
