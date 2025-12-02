package Glyvia.Glyvia.service;

import Glyvia.Glyvia.dto.RefeicaoHistoricoRequest;
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
import java.util.stream.Collectors;

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

    public List<RelatorioCaloriaRequest> gerarRelatorioCalorias(Long idUsuario, LocalDate inicio, LocalDate fim) {

        List<Refeicao> refeicoes = refeicaoRepository
                .findByUsuarioIdAndDataRefeicaoBetweenOrderByDataRefeicaoAscHoraRefeicaoAsc(
                        idUsuario, inicio, fim);

        return refeicoes.stream()
                .map(r -> new RelatorioCaloriaRequest(
                        r.getDataRefeicao(),
                        r.getHoraRefeicao(),
                        r.getCalorias()
                ))
                .toList();
    }

    public List<RelatorioCarboidratoRequest> gerarRelatorioCarboidratos(Long idUsuario, LocalDate inicio, LocalDate fim) {

        List<Refeicao> refeicoes = refeicaoRepository
                .findByUsuarioIdAndDataRefeicaoBetweenOrderByDataRefeicaoAscHoraRefeicaoAsc(
                        idUsuario, inicio, fim);

        return refeicoes.stream()
                .map(r -> new RelatorioCarboidratoRequest(
                        r.getDataRefeicao(),
                        r.getHoraRefeicao(),
                        r.getCarboidratos()
                ))
                .toList();
    }

    public List<RefeicaoHistoricoRequest> listarHistorico(Long idUsuario) {
        List<Refeicao> refeicoes =
                refeicaoRepository.findByUsuarioIdOrderByDataRefeicaoDescHoraRefeicaoDesc(idUsuario);

        return refeicoes.stream().map(r ->
                new RefeicaoHistoricoRequest(
                        r.getDescricao(),
                        r.getCalorias(),
                        r.getCarboidratos(),
                        r.getDataRefeicao(),
                        r.getHoraRefeicao()
                )
        ).collect(Collectors.toList());
    }
}