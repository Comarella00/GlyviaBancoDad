package Glyvia.Glyvia.service;

import Glyvia.Glyvia.dto.RefeicaoRequest;
import Glyvia.Glyvia.dto.RelatorioCaloriaRequest;
import Glyvia.Glyvia.dto.RelatorioCarboidratoRequest;
import Glyvia.Glyvia.model.Glicemia;
import Glyvia.Glyvia.model.Refeicao;
import Glyvia.Glyvia.model.Usuario;
import Glyvia.Glyvia.repository.GlicemiaRepository;
import Glyvia.Glyvia.repository.RefeicaoRepository;
import Glyvia.Glyvia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RefeicaoService {
    @Autowired
    private RefeicaoRepository refeicaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GlicemiaRepository glicemiaRepository;

    public Refeicao salvar(RefeicaoRequest dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Glicemia glicemia = null;
        if (dto.getIdGlicemia() != null) {
            glicemia = glicemiaRepository.findById(dto.getIdGlicemia())
                    .orElseThrow(() -> new RuntimeException("Glicemia não encontrada"));
        }

        Refeicao r = new Refeicao();
        r.setDescricao(dto.getDescricao());
        r.setCalorias(dto.getCalorias());
        r.setCarboidratos(dto.getCarboidratos());
        r.setDataRefeicao(dto.getDataRefeicao());
        r.setHoraRefeicao(dto.getHoraRefeicao());
        r.setUsuario(usuario);
        r.setGlicemia(glicemia);

        return refeicaoRepository.save(r);
    }

    public List<Refeicao> listarPorUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return refeicaoRepository.findByUsuario(usuario);
    }

    public void deletar(Long id) {
        refeicaoRepository.deleteById(id);
    }

}