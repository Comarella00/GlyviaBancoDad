package Glyvia.Glyvia.controller;

import Glyvia.Glyvia.dto.*;
import Glyvia.Glyvia.model.Glicemia;
import Glyvia.Glyvia.service.GlicemiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/glicemia")
@CrossOrigin(origins = "http://localhost:4200")
public class GlicemiaController {

    @Autowired
    private GlicemiaService glicemiaService;

    //POST do cadastro da glicemia
    @PostMapping("/cadastroGlicemia")
    public ResponseEntity<Map<String, Object>> cadastroGlicemia(@RequestBody CadastroGlicemiaRequest request) {
        Glicemia glicemia = glicemiaService.cadastroGlicemia(request);

        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Glicemia cadastrada com sucesso!");
        response.put("idGlicemia", glicemia.getIdGlicemia());
        response.put("id", glicemia.getUsuario().getId());

        return ResponseEntity.ok(response);
    }

    //GET do Histórico Recente da aba de adicionar glicemia
    @GetMapping("/historicoRecente/{idUsuario}")
    public ResponseEntity<List<HistoricoGlicemiaResponse>> historicoRecente(@PathVariable Long idUsuario) {
        List<HistoricoGlicemiaResponse> historico = glicemiaService.historicoRecente(idUsuario);
        return ResponseEntity.ok(historico);
    }

    //PUT para atualizar glicemia existente
    @PutMapping("/atualizarGlicemia")
    public ResponseEntity<Map<String, Object>> atualizarGlicemia(@RequestBody AtualizaGlicemiaRequest request) {
        Glicemia glicemiaAtualizada = glicemiaService.atualizarGlicemia(request);

        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Glicemia atualizada com sucesso!");
        response.put("idGlicemia", glicemiaAtualizada.getIdGlicemia());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/ultimaGlicemia/{idUsuario}")
    public ResponseEntity<UltimaGlicemiaRequest> getUltimaGlicemia(@PathVariable Long idUsuario) {
        UltimaGlicemiaRequest ultima = glicemiaService.getUltimaGlicemia(idUsuario);
        return ResponseEntity.ok(ultima);
    }

    @GetMapping("/mediaDiaria/{idUsuario}")
    public ResponseEntity<MediaDiariaGlicemiaRequest> getMediaDiaria(@PathVariable Long idUsuario) {
        MediaDiariaGlicemiaRequest media = glicemiaService.getMediaDiaria(idUsuario);
        return ResponseEntity.ok(media);
    }

    @GetMapping("/statusRapido/{idUsuario}")
    public ResponseEntity<StatusRapidoRequest> getStatusRapido(@PathVariable Long idUsuario) {
        StatusRapidoRequest status = glicemiaService.getStatusRapido(idUsuario);
        return ResponseEntity.ok(status);
    }

    //GET relatorio glicemia;
    //Ex: /relatorioGlicemia?idUsuario=18&inicio=2025-11-03&fim=2026-11-05
    @GetMapping("/relatorioGlicemia")
    public List<RelatorioGlicemiaRequest> gerarRelatorioGlicemia(
            @RequestParam Long idUsuario,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        return glicemiaService.gerarRelatorioGlicemia(idUsuario, inicio, fim);
    }
}
