package igoraraujo.registration.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import igoraraujo.registration.api.controller.UsuarioController;
import igoraraujo.registration.api.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationApiApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UsuarioController controller;
	@Autowired
	private UsuarioService service;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}


}
