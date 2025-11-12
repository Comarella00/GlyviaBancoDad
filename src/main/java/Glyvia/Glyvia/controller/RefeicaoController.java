package Glyvia.Glyvia.controller;

import Glyvia.Glyvia.model.Refeicao;
import Glyvia.Glyvia.service.RefeicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Glyvia.Glyvia.dto.AddCarboManualmenteRequest;

import java.util.List;


@RestController
@RequestMapping("/refeicao")
@CrossOrigin(origins = "http://localhost:4200")
public class RefeicaoController {

    @Autowired
    private RefeicaoService refeicaoService;

    @PostMapping("/adicionar")
    public ResponseEntity<Refeicao> adicionar(@RequestBody AddCarboManualmenteRequest dto) {
        Refeicao refeicao = refeicaoService.salvar(dto);
        return ResponseEntity.ok(refeicao);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Refeicao>> listar() {
        return ResponseEntity.ok(refeicaoService.listarTodos());
    }
}
