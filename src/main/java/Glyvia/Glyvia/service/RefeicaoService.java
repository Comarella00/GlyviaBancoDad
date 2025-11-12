package Glyvia.Glyvia.service;

import Glyvia.Glyvia.dto.AddCarboManualmenteRequest;
import Glyvia.Glyvia.dto.RelatorioCaloriaRequest;
import Glyvia.Glyvia.dto.RelatorioCarboidratoRequest;
import Glyvia.Glyvia.model.Refeicao;
import Glyvia.Glyvia.model.Usuario;
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

    public Refeicao salvar(AddCarboManualmenteRequest dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + dto.getUsuarioId()));

        Refeicao refeicao = new Refeicao();
        refeicao.setUsuario(usuario);
        refeicao.setDescricao(dto.getDescricao());
        refeicao.setCalorias(dto.getCalorias());
        refeicao.setCarboidratos(dto.getCarboidratos());
        refeicao.setInsulina(dto.getInsulina());
        refeicao.setDataRefeicao(dto.getDataRefeicao());
        refeicao.setHoraRefeicao(dto.getHoraRefeicao());

        return refeicaoRepository.save(refeicao);
    }

    public List<Refeicao> listarTodos() {
        return refeicaoRepository.findAll();
    }

    public List<Refeicao> buscarRelatorioRefeicao(Long idUsuario, LocalDate inicio, LocalDate fim) {
        return refeicaoRepository.findByUsuarioIdAndDataRefeicaoBetween(idUsuario, inicio, fim);
    }

    // GET relatório de calorias
    public List<RelatorioCaloriaRequest> gerarRelatorioCaloria(Long idUsuario, LocalDate dataInicio, LocalDate dataFim) {
        List<Refeicao> refeicoes = refeicaoRepository
                .findByUsuarioIdAndDataRefeicaoBetweenOrderByDataRefeicaoAscHoraRefeicaoAsc(idUsuario, dataInicio, dataFim);

        return refeicoes.stream()
                .map(r -> new RelatorioCaloriaRequest(
                        r.getDataRefeicao(),
                        r.getHoraRefeicao(),
                        r.getCalorias()))
                .collect(Collectors.toList());
    }

    // GET relatório de carboidratos
    public List<RelatorioCarboidratoRequest> gerarRelatorioCarboidrato(Long idUsuario, LocalDate dataInicio, LocalDate dataFim) {
        List<Refeicao> refeicoes = refeicaoRepository
                .findByUsuarioIdAndDataRefeicaoBetweenOrderByDataRefeicaoAscHoraRefeicaoAsc(idUsuario, dataInicio, dataFim);

        return refeicoes.stream()
                .map(r -> new RelatorioCarboidratoRequest(
                        r.getDataRefeicao(),
                        r.getHoraRefeicao(),
                        r.getCarboidratos()))
                .collect(Collectors.toList());
    }
}
