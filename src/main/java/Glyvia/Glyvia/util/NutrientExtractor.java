package Glyvia.Glyvia.util;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.OptionalDouble;

public class NutrientExtractor {

    public static OptionalDouble find(JsonNode food, String key) {
        if (!food.has("foodNutrients")) return OptionalDouble.empty();
        for (JsonNode n : food.get("foodNutrients")) {
            if (n.path("nutrientName").asText("").toLowerCase().contains(key.toLowerCase())) {
                return OptionalDouble.of(n.path("value").asDouble());
            }
        }
        return OptionalDouble.empty();
    }

    public static OptionalDouble calories(JsonNode food) {
        return find(food, "energy");
    }

    public static OptionalDouble carbs(JsonNode food) {
        return find(food, "carbohydrate");
    }
}
