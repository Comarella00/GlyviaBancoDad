package Glyvia.Glyvia.service;

import Glyvia.Glyvia.dto.AddCarboManualmenteRequest;
import Glyvia.Glyvia.model.Refeicao;
import Glyvia.Glyvia.model.Usuario;
import Glyvia.Glyvia.repository.RefeicaoRepository;
import Glyvia.Glyvia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefeicaoService {
    @Autowired
    private RefeicaoRepository refeicaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Refeicao salvar(AddCarboManualmenteRequest dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + dto.getUsuarioId()));

        Refeicao refeicao = new Refeicao();
        refeicao.setUsuario(usuario);
        refeicao.setDescricao(dto.getDescricao());
        refeicao.setCalorias(dto.getCalorias());
        refeicao.setCarboidratos(dto.getCarboidratos());
        refeicao.setInsulina(dto.getInsulina());
        refeicao.setDataRefeicao(dto.getDataRefeicao());
        refeicao.setHoraRefeicao(dto.getHoraRefeicao());

        return refeicaoRepository.save(refeicao);
    }

    public List<Refeicao> listarTodos() {
        return refeicaoRepository.findAll();
    }
}
