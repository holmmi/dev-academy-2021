package fi.solita.devacademy2021.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.solita.devacademy2021.model.Name;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Json {
    @Bean
    public List<Name> nameList() throws IOException {
        InputStream is = this.getClass().getResourceAsStream("/json/names.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(is);
        is.close();
        JsonNode arrayNode = jsonNode.get("names");
        List<Name> names = new ArrayList<>();
        if (arrayNode.isArray()) {
            for (JsonNode node : arrayNode) {
                names.add(new Name(node.get("name").asText(), node.get("amount").asInt()));
            }
        }
        return names;
    }
}
