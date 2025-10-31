package Glyvia.Glyvia.service;

import Glyvia.Glyvia.dto.CadastroRequest;
import Glyvia.Glyvia.dto.LoginRequest;
import Glyvia.Glyvia.dto.UsuarioResponse;
import Glyvia.Glyvia.model.Usuario;
import Glyvia.Glyvia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrarInicial(CadastroRequest request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());
        usuarioRepository.save(usuario);
    }

    public String login(LoginRequest request) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(request.getEmail());
        if (usuario.isEmpty()) {
            return "Usuário não encontrado!";
        }

        if (!usuario.get().getSenha().equals(request.getSenha())) {
            return "Senha incorreta!";
        }

        return "Login realizado com sucesso!";
    }

    public List<UsuarioResponse> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(u -> new UsuarioResponse(u.getId(), u.getEmail()))
                .collect(Collectors.toList());
    }
}
