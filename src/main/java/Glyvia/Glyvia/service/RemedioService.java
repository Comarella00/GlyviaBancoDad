package Glyvia.Glyvia.service;

import Glyvia.Glyvia.dto.RemedioRequest;
import Glyvia.Glyvia.model.Remedio;
import Glyvia.Glyvia.repository.RemedioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemedioService {
    @Autowired
    private RemedioRepository remedioRepository;

    public Remedio salvar(RemedioRequest dto) {
        Remedio r = new Remedio();
        r.setDescricao(dto.getDescricao());
        r.setDiasDaSemana(dto.getDiasDaSemana());
        r.setHorario(dto.getHorario());
        return remedioRepository.save(r);
    }

    public List<Remedio> listarTodos() {
        return remedioRepository.findAll();
    }

    public List<Remedio> listarPorUsuario(Long idUsuario) {
        return remedioRepository.findByUsuario_Id(idUsuario);
    }

    public Remedio buscarPorId(Long id) {
        return remedioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Remédio não encontrado!"));
    }

    public Remedio atualizar(Long id, RemedioRequest dto) {
        Remedio r = buscarPorId(id);
        r.setDescricao(dto.getDescricao());
        r.setDiasDaSemana(dto.getDiasDaSemana());
        r.setHorario(dto.getHorario());
        return remedioRepository.save(r);
    }

    public void deletar(Long id) {
        remedioRepository.deleteById(id);
    }

    public Remedio marcarTomado(Long id) {
        Remedio r = buscarPorId(id);
        r.setTomado(true);
        return remedioRepository.save(r);
    }

    public void resetarTomados() {
        List<Remedio> lista = remedioRepository.findAll();
        lista.forEach(r -> r.setTomado(false));
        remedioRepository.saveAll(lista);
    }
}
