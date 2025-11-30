package Glyvia.Glyvia.controller;

import Glyvia.Glyvia.dto.RemedioRequest;
import Glyvia.Glyvia.model.Remedio;
import Glyvia.Glyvia.model.Usuario;
import Glyvia.Glyvia.repository.RemedioRepository;
import Glyvia.Glyvia.repository.UsuarioRepository;
import Glyvia.Glyvia.service.RemedioService;
import Glyvia.Glyvia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/remedios")
@CrossOrigin(origins = "http://localhost:4200")
public class RemedioController {
    @Autowired
    private RemedioService remedioService;

    @Autowired
    private RemedioRepository remedioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //POST para adicionar remédio
    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarRemedio(@RequestBody Remedio remedio) {
        if (remedio.getUsuario() == null || remedio.getUsuario().getId() == null) {
            return ResponseEntity.badRequest().body("Usuário é obrigatório.");
        }

        Usuario usuario = usuarioRepository.findById(remedio.getUsuario().getId())
                .orElse(null);

        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }

        remedio.setUsuario(usuario);
        Remedio salvo = remedioRepository.save(remedio);

        return ResponseEntity.ok(salvo);
    }

    //GET listar todos
    @GetMapping("/listar")
    public ResponseEntity<List<Remedio>> listar() {
        return ResponseEntity.ok(remedioService.listarTodos());
    }

    //GET por ID
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Remedio>> listarPorUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(remedioService.listarPorUsuario(id));
    }

    //PUT atualizar remédio
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Remedio> atualizar(
            @PathVariable Long id,
            @RequestBody RemedioRequest dto
    ) {
        return ResponseEntity.ok(remedioService.atualizar(id, dto));
    }

    //PUT marcar como tomado
    @PutMapping("/tomado/{id}")
    public ResponseEntity<Remedio> marcarTomado(@PathVariable Long id) {
        return ResponseEntity.ok(remedioService.marcarTomado(id));
    }

    //DELETE deletar remédio
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        remedioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    //PUT resetar todos os "tomado" para false
    @PutMapping("/resetar")
    public ResponseEntity<Void> resetarTomados() {
        remedioService.resetarTomados();
        return ResponseEntity.noContent().build();
    }
}
