package Glyvia.Glyvia.controller;

import Glyvia.Glyvia.dto.RefeicaoHistoricoRequest;
import Glyvia.Glyvia.dto.RefeicaoRequest;
import Glyvia.Glyvia.dto.RelatorioCaloriaRequest;
import Glyvia.Glyvia.dto.RelatorioCarboidratoRequest;
import Glyvia.Glyvia.model.Refeicao;
import Glyvia.Glyvia.service.RefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/refeicao")
@CrossOrigin(origins = "http://localhost:4200")
public class RefeicaoController {
    @Autowired
    private RefeicaoService refeicaoService;

    @PostMapping("/adicionar")
    public Refeicao adicionar(@RequestBody RefeicaoRequest dto) {
        return refeicaoService.salvar(dto);
    }

    @GetMapping("/listar/{idUsuario}")
    public List<Refeicao> listar(@PathVariable Long idUsuario) {
        return refeicaoService.listarPorUsuario(idUsuario);
    }

    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        refeicaoService.deletar(id);
        return "Refeição removida com sucesso!";
    }

    @GetMapping("/relatorio-calorias")
    public List<RelatorioCaloriaRequest> gerarRelatorioCalorias(
            @RequestParam Long idUsuario,
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        return refeicaoService.gerarRelatorioCalorias(idUsuario, inicio, fim);
    }

    @GetMapping("/relatorio-carboidratos")
    public List<RelatorioCarboidratoRequest> gerarRelatorioCarboidratos(
            @RequestParam Long idUsuario,
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim) {
        return refeicaoService.gerarRelatorioCarboidratos(idUsuario, inicio, fim);
    }

    @GetMapping("/historico/{idUsuario}")
    public List<RefeicaoHistoricoRequest> listarHistorico(@PathVariable Long idUsuario) {
        return refeicaoService.listarHistorico(idUsuario);
    }
}