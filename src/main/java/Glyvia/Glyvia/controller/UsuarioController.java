package Glyvia.Glyvia.controller;

import Glyvia.Glyvia.dto.CadastroUsuarioRequest;
import Glyvia.Glyvia.dto.LoginRequest;
import Glyvia.Glyvia.dto.PerguntasRequest;
import Glyvia.Glyvia.dto.UsuarioResponse;
import Glyvia.Glyvia.model.Usuario;
import Glyvia.Glyvia.repository.UsuarioRepository;
import Glyvia.Glyvia.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //POST com o cadastro do usuário
    @PostMapping("/cadastro")
    public ResponseEntity<Map<String, Object>> cadastrar(@RequestBody @Valid CadastroUsuarioRequest request) {
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

    //POST com as perguntas vinculadas ao id do usuário
    @PostMapping("/perguntas/{id}")
    public ResponseEntity<Map<String, String>> responderPerguntas(@PathVariable Long id, @RequestBody PerguntasRequest request) {
        usuarioService.atualizarPerguntas(id, request);

        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Dados complementares salvos com sucesso!");

        return ResponseEntity.ok(response);
    }

    //GET para listar os usuários existentes
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    //POST com o login do usuário existente no bd
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Usuario> usuario = usuarioService.loginUsuario(request);

        if (usuario.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensagem", "Login realizado com sucesso!");
            response.put("id", usuario.get().getId());
            response.put("email", usuario.get().getEmail());
            response.put("temaPreferido", usuario.get().getTemaPreferido()); // 🔹 Adiciona o tema
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("erro", "Credenciais inválidas!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    @PutMapping("/{id}/tema")
    public ResponseEntity<Map<String, String>> atualizarTema(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String tema = body.get("tema");
        usuarioService.atualizarTema(id, tema);

        Map<String, String> response = new HashMap<>();
        response.put("mensagem", "Tema atualizado com sucesso!");
        return ResponseEntity.ok(response);
    }


}