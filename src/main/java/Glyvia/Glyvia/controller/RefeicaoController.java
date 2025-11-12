package Glyvia.Glyvia.controller;

import Glyvia.Glyvia.dto.RelatorioCaloriaRequest;
import Glyvia.Glyvia.dto.RelatorioCarboidratoRequest;
import Glyvia.Glyvia.model.Refeicao;
import Glyvia.Glyvia.repository.RefeicaoRepository;
import Glyvia.Glyvia.service.RefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Glyvia.Glyvia.dto.AddCarboManualmenteRequest;

import java.util.List;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/refeicao")
@CrossOrigin(origins = "http://localhost:4200")
public class RefeicaoController {

    @Autowired
    private RefeicaoService refeicaoService;

    @Autowired
    private RefeicaoRepository refeicaoRepository;

    //POST para add refeição manualmente
    @PostMapping("/adicionar")
    public ResponseEntity<Refeicao> adicionar(@RequestBody AddCarboManualmenteRequest dto) {
        Refeicao refeicao = refeicaoService.salvar(dto);
        return ResponseEntity.ok(refeicao);
    }

    //GET em todas as refeições
    @GetMapping("/listar")
    public ResponseEntity<List<Refeicao>> listar() {
        return ResponseEntity.ok(refeicaoService.listarTodos());
    }

    //GET em relatório filtrado por intervalo de datas e id do usuário
    //Ex: http://localhost:8080/Glyvia/refeicao/relatorioRefeicao/18?inicio=2024-11-01&fim=2025-11-12
    @GetMapping("/relatorio-refeicao")
    public ResponseEntity<List<Refeicao>> relatorioRefeicao(
            @PathVariable Long idUsuario,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        List<Refeicao> refeicoes = refeicaoService.buscarRelatorioRefeicao(idUsuario, inicio, fim);
        return ResponseEntity.ok(refeicoes);
    }

    //GET de relatório de caloria
    @GetMapping("/relatorio-caloria")
    public ResponseEntity<List<RelatorioCaloriaRequest>> relatorioCalorias(
            @RequestParam Long idUsuario,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        List<RelatorioCaloriaRequest> relatorio = refeicaoService.gerarRelatorioCaloria(idUsuario, inicio, fim);
        return ResponseEntity.ok(relatorio);
    }

    //GET de relatório de carboidrato
    @GetMapping("/relatorio-carboidrato")
    public ResponseEntity<List<RelatorioCarboidratoRequest>> relatorioCarboidratos(
            @RequestParam Long idUsuario,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        List<RelatorioCarboidratoRequest> relatorio = refeicaoService.gerarRelatorioCarboidrato(idUsuario, inicio, fim);
        return ResponseEntity.ok(relatorio);
    }

}
