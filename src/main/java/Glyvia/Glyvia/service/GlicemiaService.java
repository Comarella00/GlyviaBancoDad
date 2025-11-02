package Glyvia.Glyvia.service;

import Glyvia.Glyvia.dto.CadastroGlicemiaRequest;
import Glyvia.Glyvia.model.Glicemia;
import Glyvia.Glyvia.model.Usuario;
import Glyvia.Glyvia.repository.GlicemiaRepository;
import Glyvia.Glyvia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlicemiaService {
    @Autowired
    private GlicemiaRepository glicemiaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Glicemia cadastroGlicemia (CadastroGlicemiaRequest request){
        Usuario usuario = usuarioRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Glicemia glicemia = new Glicemia();
        glicemia.setValorGlicemia(request.getValorGlicemia());
        glicemia.setDataGlicemia(request.getDataGlicemia());
        glicemia.setHoraGlicemia(request.getHoraGlicemia());
        glicemia.setUsuario(usuario); // 🔗 associa o usuário

        return glicemiaRepository.save(glicemia);
    }


}
