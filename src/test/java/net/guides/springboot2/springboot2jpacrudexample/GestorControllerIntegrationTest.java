package net.guides.springboot2.springboot2jpacrudexample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import br.com.vinicius.crudGestoresBackend.Application;
import br.com.vinicius.crudGestoresBackend.model.Gestor;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GestorControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllEmployees() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/gestores",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetGestorById() {
		Gestor gestor = restTemplate.getForObject(getRootUrl() + "/gestores/1", Gestor.class);
		System.out.println(gestor.getNome());
		assertNotNull(gestor);
	}

	@Test
	public void testCreateEmployee() {
		Gestor gestor = new Gestor();
		gestor.setNome("teste");
		gestor.setMatricula("teste");
		gestor.setNascimento("teste");
		gestor.setSetor("teste");

		ResponseEntity<Gestor> postResponse = restTemplate.postForEntity(getRootUrl() + "/gestores", gestor, Gestor.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEmployee() {
		int id = 1;
		Gestor gestor = restTemplate.getForObject(getRootUrl() + "/gestores/" + id, Gestor.class);
		gestor.setNome("gestor1");
		gestor.setMatricula("123");

		restTemplate.put(getRootUrl() + "/gestores/" + id, gestor);

		Gestor updatedEmployee = restTemplate.getForObject(getRootUrl() + "/gestores/" + id, Gestor.class);
		assertNotNull(updatedEmployee);
	}

	@Test
	public void testDeleteEmployee() {
		int id = 2;
		Gestor gestores = restTemplate.getForObject(getRootUrl() + "/gestores/" + id, Gestor.class);
		assertNotNull(gestores);

		restTemplate.delete(getRootUrl() + "/employees/" + id);

		try {
			gestores = restTemplate.getForObject(getRootUrl() + "/gestores/" + id, Gestor.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
