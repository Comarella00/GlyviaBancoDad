package Glyvia.Glyvia.controller;

import Glyvia.Glyvia.dto.ConsultaRequest;
import Glyvia.Glyvia.model.Consulta;
import Glyvia.Glyvia.model.Remedio;
import Glyvia.Glyvia.repository.ConsultaRepository;
import Glyvia.Glyvia.repository.RemedioRepository;
import Glyvia.Glyvia.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
@CrossOrigin(origins = "*")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private RemedioRepository remedioRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping("/adicionar")
    public Consulta adicionar(@RequestBody ConsultaRequest dto) {
        return consultaService.adicionarConsulta(dto);
    }

    @GetMapping("/listarPorUsuario")
    public List<Consulta> listarConsultasPorUsuario(@RequestParam Long idUsuario) {
        return consultaRepository.findByUsuarioId(idUsuario);
    }

    @GetMapping("/remedios/listarPorUsuario")
    public List<Remedio> listarRemediosPorUsuario(@RequestParam Long idUsuario) {
        return remedioRepository.findByUsuario_Id(idUsuario);
    }

}