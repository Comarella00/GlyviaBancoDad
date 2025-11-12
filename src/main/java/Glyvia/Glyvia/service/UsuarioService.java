package Glyvia.Glyvia.service;

import Glyvia.Glyvia.dto.*;
import Glyvia.Glyvia.model.Usuario;
import Glyvia.Glyvia.repository.GlicemiaRepository;
import Glyvia.Glyvia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GlicemiaRepository glicemiaRepository;

    //Salva a foto de perfil nesse path
    private final Path uploadDir = Paths.get(".\\uploads\\fotos");

    //Cadastro do usuário
    public Usuario cadastrarInicial(CadastroUsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());
        return usuarioRepository.save(usuario);
    }

    //Login do usuário existente no bd
    public Optional<Usuario> loginUsuario(LoginRequest request) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(request.getEmail());
        if (usuario.isPresent() && usuario.get().getSenha().equals(request.getSenha())) {
            return usuario;
        }
        return Optional.empty();
    }

    //Alterar de null para informações concretas as perguntas básicas pro usuário
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
        usuario.setFs(request.getFs());
        usuario.setDataNascimento(request.getDataNascimento());

        usuarioRepository.save(usuario);
    }

    //Lista as perguntas que foram feitas ao usuário
    public List<UsuarioResponse> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(u -> new UsuarioResponse(
                        u.getId(),
                        u.getEmail(),
                        u.getNome(),
                        u.getGenero(),
                        u.getTipoInsulina(),
                        u.getViaAplicacao(),
                        u.getPesoAtual(),
                        u.getAltura(),
                        u.getMetaGlicemica(),
                        u.getIcr(),
                        u.getFs(),
                        u.getDataNascimento(),
                        u.getFotoPerfil()
                ))
                .collect(Collectors.toList());
    }

    //Atualiza o tema dark/light mode
    public void atualizarTema(Long id, String tema) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        usuario.setTemaPreferido(tema);
        usuarioRepository.save(usuario);
    }

    //Busca por ID
    public Optional<UsuarioResponse> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    UsuarioResponse response = new UsuarioResponse();
                    response.setId(usuario.getId());
                    response.setNome(usuario.getNome());
                    response.setEmail(usuario.getEmail());
                    return response;
                });
    }

    //Salva a foto de perfil
    public String salvarFoto(Long id, MultipartFile foto) throws IOException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        //Verifica tipo MIME permitido
        String tipo = foto.getContentType();
        List<String> tiposPermitidos = List.of("image/jpeg", "image/png");

        if (tipo == null || !tiposPermitidos.contains(tipo)) {
            throw new IOException("Formato de arquivo inválido. Envie apenas JPG ou PNG.");
        }

        //Cria nome seguro para o arquivo
        String originalName = foto.getOriginalFilename().replaceAll("\\s+", "_");
        String nomeArquivo = "usuario_" + id + "_" + originalName;

        Path uploadDir = Paths.get(System.getProperty("user.dir"), "uploads", "fotos");
        Files.createDirectories(uploadDir);
        Path caminho = uploadDir.resolve(nomeArquivo);
        Files.copy(foto.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);
        usuario.setFotoPerfil(nomeArquivo);
        usuarioRepository.save(usuario);
        return nomeArquivo;
    }

    //Busca a foto de perfil por ID
    public UrlResource getFotoUsuario(Long id) throws IOException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IOException("Usuário não encontrado"));
        if (usuario.getFotoPerfil() == null || usuario.getFotoPerfil().isEmpty()) {
            throw new IOException("Usuário não possui foto");
        }
        Path caminhoFoto = uploadDir.resolve(usuario.getFotoPerfil()).toAbsolutePath();
        UrlResource resource = new UrlResource(caminhoFoto.toUri());
        if (!resource.exists()) {
            throw new FileNotFoundException("Arquivo não encontrado: " + caminhoFoto);
        }
        if (!resource.isReadable()) {
            throw new IOException("Não foi possível ler o arquivo: " + caminhoFoto);
        }
        return resource;
    }
}
