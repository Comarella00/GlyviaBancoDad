package Glyvia.Glyvia.controller;

import Glyvia.Glyvia.service.FdcService;
import Glyvia.Glyvia.util.NutrientExtractor;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/alimento")
public class AlimentoController {

    private final FdcService fdcService;

    public AlimentoController(FdcService fdcService) {
        this.fdcService = fdcService;
    }

    @GetMapping("/buscar")
    public JsonNode buscar(@RequestParam String nome) {
        return fdcService.searchFoods(nome, 10);
    }

    @GetMapping("/{fdcId}")
    public Map<String, Object> detalhes(@PathVariable Long fdcId) {
        JsonNode food = fdcService.getFoodById(fdcId);

        Map<String, Object> result = new HashMap<>();
        result.put("nome", food.path("description").asText());
        result.put("calorias", NutrientExtractor.calories(food).orElse(0));
        result.put("carboidratos", NutrientExtractor.carbs(food).orElse(0));
        result.put("raw", food);

        return result;
    }
}
