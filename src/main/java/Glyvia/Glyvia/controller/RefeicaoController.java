package Glyvia.Glyvia.controller;

import Glyvia.Glyvia.dto.RefeicaoRequest;
import Glyvia.Glyvia.dto.RelatorioCaloriaRequest;
import Glyvia.Glyvia.dto.RelatorioCarboidratoRequest;
import Glyvia.Glyvia.model.Refeicao;
import Glyvia.Glyvia.service.RefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}