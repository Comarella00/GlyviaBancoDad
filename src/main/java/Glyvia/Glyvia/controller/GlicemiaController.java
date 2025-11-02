package Glyvia.Glyvia.controller;

import Glyvia.Glyvia.dto.CadastroGlicemiaRequest;
import Glyvia.Glyvia.model.Glicemia;
import Glyvia.Glyvia.model.Usuario;
import Glyvia.Glyvia.service.GlicemiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/glicemia")
@CrossOrigin(origins = "http://localhost:4200")
public class GlicemiaController {

    @Autowired
    private GlicemiaService glicemiaService;

    @PostMapping("/cadastroGlicemia")
    public ResponseEntity<Map<String, Object>> cadastroGlicemia(@RequestBody CadastroGlicemiaRequest request) {
        Glicemia glicemia = glicemiaService.cadastroGlicemia(request);

        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Glicemia cadastrada com sucesso!");
        response.put("idGlicemia", glicemia.getIdGlicemia());
        response.put("id", glicemia.getUsuario().getId());

        return ResponseEntity.ok(response);

    }
}
