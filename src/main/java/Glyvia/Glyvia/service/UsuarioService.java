package Glyvia.Glyvia.service;

import Glyvia.Glyvia.dto.CadastroUsuarioRequest;
import Glyvia.Glyvia.dto.LoginRequest;
import Glyvia.Glyvia.dto.PerguntasRequest;
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

    public Usuario cadastrarInicial(CadastroUsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());
        return usuarioRepository.save(usuario);
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

    public void atualizarPerguntas(Long id, PerguntasRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        usuario.setNome(request.getNome());
        usuario.setGenero(request.getGenero());
        usuario.setTipoInsulina(request.getTipoInsulina());
        usuario.setViaAplicacao(request.getViaAplicacao());
        usuario.setPesoAtual(request.getPesoAtual());
        usuario.setAltura(request.getAltura());
        usuario.setMetaGlicemica(request.getMetaGlicemica());
        usuario.setIcr(request.getIcr());
        usuario.setDataNascimento(request.getDataNascimento());

        usuarioRepository.save(usuario);

    }

    public List<UsuarioResponse> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(u -> new UsuarioResponse(u.getId(),
                        u.getEmail(),
                        u.getNome(),
                        u.getGenero(),
                        u.getTipoInsulina(),
                        u.getViaAplicacao(),
                        u.getPesoAtual(),
                        u.getAltura(),
                        u.getMetaGlicemica(),
                        u.getIcr(),
                        u.getDataNascimento()))
                .collect(Collectors.toList());
    }
}
