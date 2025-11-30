package Glyvia.Glyvia.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class FdcService {

    @Value("${fdc.api-key}")
    private String apiKey;

    @Value("${fdc.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public FdcService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JsonNode searchFoods(String query, int pageSize) {
        String url = baseUrl + "/v1/foods/search?api_key=" + apiKey;

        Map<String, Object> body = Map.of(
                "query", query,
                "pageSize", pageSize
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(url, entity, JsonNode.class);
    }

    public JsonNode getFoodById(Long fdcId) {
        String url = baseUrl + "/v1/food/" + fdcId + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, JsonNode.class);
    }
}
