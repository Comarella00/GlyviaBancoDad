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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.io.IOException;

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

    //PUT tema light/dark mode
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

    //GET dados por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(usuario -> ResponseEntity.ok(usuario))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body((UsuarioResponse) Map.of("erro", "Usuário não encontrado")));
    }

    //POST da foto de perfil
    @PostMapping("/{id}/foto")
    public ResponseEntity<String> uploadFoto(@PathVariable Long id, @RequestParam("foto") MultipartFile foto) throws IOException {
        String nomeArquivo = usuarioService.salvarFoto(id, foto);
        return ResponseEntity.ok(nomeArquivo);
    }


    //Obter/exibir foto do usuário
    @GetMapping("/{id}/foto")
    public ResponseEntity<Resource> getFotoUsuario(@PathVariable Long id) {
        try {
            Resource resource = usuarioService.getFotoUsuario(id);

            // Detecta tipo MIME automaticamente (image/png, image/jpeg, etc)
            String contentType = "application/octet-stream";
            try {
                contentType = resource.getURL().openConnection().getContentType();
            } catch (IOException ignored) {}

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}