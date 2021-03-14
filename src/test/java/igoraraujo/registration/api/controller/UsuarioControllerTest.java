package igoraraujo.registration.api.controller;

import igoraraujo.registration.api.model.Usuario;
import igoraraujo.registration.api.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    @Test
    public void findAllUser_ShouldSucessAndContainString() throws Exception {
        String[] tel = {"12345"};
        Usuario test = new Usuario("Igor", "Araujo", 2020, tel);
        test.setId(1);
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        users.add(test);

        when(service.findAll()).thenReturn(users);

        this.mockMvc.perform(get("/api")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Igor")));
    }

    @Test
    public void findAllUser_ShouldFail() throws Exception {
        ArrayList<Usuario> users = new ArrayList<Usuario>();

        when(service.findAll()).thenReturn(users);

        this.mockMvc.perform(get("/api")).andExpect(status().isNotFound());
    }

    @Test
    public void findById_ShouldSucessAndContainString() throws Exception {
        String[] tel = {"12345"};
        Usuario test = new Usuario("Igor", "Araujo", 2020, tel);
        test.setId(25);

        when(service.findById(25)).thenReturn(Optional.of(test));

        this.mockMvc.perform(get("/api/id/25")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Igor")))
                .andExpect(content().string(containsString("Araujo")))
                .andExpect(content().string(containsString("2020")))
                .andExpect(content().string(containsString("[\"12345\"]")));
    }

    @Test
    public void findById_ShouldFail() throws Exception {
        when(service.findById(25)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/api/id/25")).andExpect(status().isNotFound());
    }

    @Test
    public void findByName_ShouldSucessAndContainString() throws Exception {
        String[] tel = {"12345"};
        Usuario test = new Usuario("Igor", "Araujo", 2020, tel);
        test.setId(1);
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        users.add(test);

        when(service.findByNome("Igor")).thenReturn(users);

        this.mockMvc.perform(get("/api/nome/Igor")).andExpect(status().isOk())
                .andExpect(content().string(containsString("1")))
                .andExpect(content().string(containsString("Igor")))
                .andExpect(content().string(containsString("Araujo")))
                .andExpect(content().string(containsString("2020")))
                .andExpect(content().string(containsString("[\"12345\"]")));
    }

    @Test
    public void findByName_ShouldFailBecauseArrayIsEmpty() throws Exception {
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        when(service.findByNome("Igor")).thenReturn(users);

        this.mockMvc.perform(get("/api/nome/Igor")).andExpect(status().isNotFound());

    }

    @Test
    public void findByMatricula_ShouldSucessAndContainString() throws Exception {
        String[] tel = {"12345"};
        Usuario test = new Usuario("Igor", "Araujo", 2020, tel);
        test.setId(1);
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        users.add(test);

        when(service.findByMatricula(2020)).thenReturn(users);

        this.mockMvc.perform(get("/api/matricula/2020")).andExpect(status().isOk())
                .andExpect(content().string(containsString("1")))
                .andExpect(content().string(containsString("Igor")))
                .andExpect(content().string(containsString("Araujo")))
                .andExpect(content().string(containsString("2020")))
                .andExpect(content().string(containsString("[\"12345\"]")));
    }

    @Test
    public void findByMatricula_ShouldFailBecauseArrayIsEmpty() throws Exception {
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        when(service.findByMatricula(2020)).thenReturn(users);

        this.mockMvc.perform(get("/api/matricula/2020")).andExpect(status().isNotFound());
    }

    @Test
    public void salvar_ShouldSucess() throws Exception {
        this.mockMvc.perform(post("/api").contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nome\": \"Igor\", \"sobrenome\": \"Araujo\",\"matricula\": 2020, \"telefone\": [\"1234\"] }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void salvar_ShouldFailBecauseValidationFailed() throws Exception {
        this.mockMvc.perform(post("/api").contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nome\": \"Ig\", \"sobrenome\": \"Araujo\",\"matricula\": 2020, \"telefone\": [\"1234\"] }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void update_ShouldFailBecauseCantFindId() throws Exception {
        String[] tel = {"12345"};
        Usuario test = new Usuario("Igor", "Araujo", 2020, tel);
        test.setId(25);

        when(service.update(25,test)).thenReturn(Optional.empty());

        this.mockMvc.perform(post("/api/25").contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nome\": \"Igor\", \"sobrenome\": \"Araujo\",\"matricula\": 2020, \"telefone\": [\"12345\"] }"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_ShouldSucess() throws Exception {

        when(service.deleteById(25)).thenReturn(HttpStatus.OK);

        this.mockMvc.perform(delete("/api/25").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_ShouldFailBecauseCantFindId() throws Exception {

        when(service.deleteById(25)).thenReturn(HttpStatus.NOT_FOUND);

        this.mockMvc.perform(delete("/api/25").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
