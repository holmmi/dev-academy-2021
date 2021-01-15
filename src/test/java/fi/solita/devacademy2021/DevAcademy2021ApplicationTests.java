package fi.solita.devacademy2021;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.solita.devacademy2021.model.Name;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DevAcademy2021ApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void mostPopularNameShouldBeFirst() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + this.port + "/api/name/popular",
				Name[].class)[0].getName()).isEqualTo("Ville");
	}

	@Test
	public void firstNameShouldBeAnna() {
		assertThat(this.restTemplate.getForObject("http://localhost:" + this.port + "/api/name/alphabetical",
				String[].class)[0]).isEqualTo("Anna");
	}

	@Test
	public void amountOfNamesShouldBeReturned() throws JsonProcessingException {
		String json = this.restTemplate.getForObject("http://localhost:" + this.port + "/api/name/amount", String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		assertThat(objectMapper.readValue(json, new TypeReference<HashMap<String, Integer>>() {}).get("names")).isEqualTo(211);
	}

	@Test
	public void amountOfSpecificNamesShouldBeReturned() throws JsonProcessingException {
		String json = this.restTemplate.getForObject("http://localhost:" + this.port + "/api/name/amount?name=Ville", String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		assertThat(objectMapper.readValue(json, new TypeReference<HashMap<String, Integer>>() {}).get("names")).isEqualTo(24);
	}
}
