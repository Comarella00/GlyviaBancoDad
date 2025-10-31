package Glyvia.Glyvia.controller;

import Glyvia.Glyvia.dto.CadastroRequest;
import Glyvia.Glyvia.dto.LoginRequest;
import Glyvia.Glyvia.dto.PerguntasRequest;
import Glyvia.Glyvia.dto.UsuarioResponse;
import Glyvia.Glyvia.model.Usuario;
import Glyvia.Glyvia.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<Map<String, Object>>  cadastrar(@RequestBody @Valid CadastroRequest request) {
        if (!request.getSenha().equals(request.getConfirmarSenha())) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("mensagem", "As senhas não coincidem!");
            return ResponseEntity.badRequest().body(erro);
        }

        Usuario usuario = usuarioService.cadastrarInicial(request);

        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Usuário cadastrado com sucesso!");
        response.put("id", usuario.getId());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String resultado = usuarioService.login(request);
        if (resultado.startsWith("Login")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.badRequest().body(resultado);
        }
    }

    @PostMapping("/perguntas/{id}")
    public ResponseEntity<Map<String, String>> responderPerguntas(@PathVariable Long id, @RequestBody PerguntasRequest request) {
        usuarioService.atualizarPerguntas(id, request);

        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Dados complementares salvos com sucesso!");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

}
