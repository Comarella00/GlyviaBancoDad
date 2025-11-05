package Glyvia.Glyvia.service;

import Glyvia.Glyvia.dto.*;
import Glyvia.Glyvia.model.Glicemia;
import Glyvia.Glyvia.model.Usuario;
import Glyvia.Glyvia.repository.GlicemiaRepository;
import Glyvia.Glyvia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GlicemiaService {
    @Autowired
    private GlicemiaRepository glicemiaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Adiciona a glicemia
    public Glicemia cadastroGlicemia(CadastroGlicemiaRequest request) {
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
                        g.getIdGlicemia(),
                        g.getValorGlicemia(),
                        g.getDataGlicemia(),
                        g.getHoraGlicemia()))
                .collect(Collectors.toList());
    }

    //UPDATE da glicemia
    public Glicemia atualizarGlicemia(AtualizaGlicemiaRequest request) {
        Glicemia glicemia = glicemiaRepository.findById(request.getIdGlicemia())
                .orElseThrow(() -> new RuntimeException("Glicemia não encontrada"));

        if (request.getValorGlicemia() != null)
            glicemia.setValorGlicemia(request.getValorGlicemia());

        if (request.getDataGlicemia() != null)
            glicemia.setDataGlicemia(request.getDataGlicemia());

        if (request.getHoraGlicemia() != null)
            glicemia.setHoraGlicemia(request.getHoraGlicemia());

        if (request.getChecagemGlicemia() != null)
            glicemia.setChecagemGlicemia(request.getChecagemGlicemia());

        // Verifica se o idUsuario foi enviado e atualiza o vínculo (geralmente não muda)
        if (request.getIdUsuario() != null) {
            Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            glicemia.setUsuario(usuario);
        }
        return glicemiaRepository.save(glicemia);
    }

    public UltimaGlicemiaRequest getUltimaGlicemia(Long idUsuario) {
        Glicemia ultima = glicemiaRepository.findTopByUsuarioIdOrderByDataGlicemiaDescHoraGlicemiaDesc(idUsuario)
                .orElseThrow(() -> new RuntimeException("Nenhum registro de glicemia encontrado"));

        return new UltimaGlicemiaRequest(ultima.getValorGlicemia(), ultima.getHoraGlicemia());
    }

    public MediaDiariaGlicemiaRequest getMediaDiaria(Long idUsuario) {
        LocalDate hoje = LocalDate.now();
        List<Glicemia> glicemiasHoje = glicemiaRepository
                .findByUsuarioIdAndDataGlicemia(idUsuario, hoje);

        if (glicemiasHoje.isEmpty()) {
            return new MediaDiariaGlicemiaRequest(0.0);
        }

        double media = glicemiasHoje.stream()
                .mapToDouble(Glicemia::getValorGlicemia)
                .average()
                .orElse(0.0);

        return new MediaDiariaGlicemiaRequest(media);
    }

    public StatusRapidoRequest getStatusRapido(Long idUsuario) {
        Glicemia ultima = glicemiaRepository.findTopByUsuarioIdOrderByDataGlicemiaDescHoraGlicemiaDesc(idUsuario)
                .orElseThrow(() -> new RuntimeException("Nenhum registro de glicemia encontrado"));

        boolean dentroFaixa = ultima.getValorGlicemia() >= 70 && ultima.getValorGlicemia() <= 180;
        return new StatusRapidoRequest(dentroFaixa);
    }
}