package igoraraujo.registration.api.controller;

import igoraraujo.registration.api.model.Usuario;
import igoraraujo.registration.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService repository;

    @GetMapping("/api")
    public ResponseEntity allUsers() {
        return repository.findAll();
    }

    @GetMapping(path = "/api/id/{codigo}")
    public ResponseEntity consultar(@PathVariable String codigo) {
        return repository.findById(codigo);
    }

    @GetMapping("/api/nome/{nome}")
    public ResponseEntity findByName(@PathVariable String nome) {
        return repository.findByNome(nome);
    }

    @GetMapping("/api/matricula/{numero}")
    public ResponseEntity findByMatricula(@PathVariable Integer numero) {
        return repository.findByMatricula(numero);
    }

    @PostMapping(path = "/api")
    public ResponseEntity salvar(@Valid @RequestBody Usuario user) {
        return repository.save(user);
    }

    @PostMapping("/api/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Usuario user) {
        return repository.update(id, user);
    }

    @DeleteMapping("/api/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
