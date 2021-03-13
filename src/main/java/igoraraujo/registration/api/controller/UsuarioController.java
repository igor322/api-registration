package igoraraujo.registration.api.controller;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import igoraraujo.registration.api.model.Usuario;
import igoraraujo.registration.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.sql.SQLException;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/api")
    public ResponseEntity allUsers() {
        var users = service.findAll();
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(path = "/api/id/{codigo}")
    public ResponseEntity consultar(@PathVariable Integer codigo) {
        return service.findById(codigo)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/nome/{nome}")
    public ResponseEntity findByName(@PathVariable String nome) {
        var users = service.findByNome(nome);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/api/matricula/{numero}")
    public ResponseEntity findByMatricula(@PathVariable Integer numero) {
        var users = service.findByMatricula(numero);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(users);
    }

    @PostMapping(path = "/api")
    public void salvar(@Valid @RequestBody Usuario user){
        service.save(user);
    }

    @PostMapping("/api/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Usuario user) {
        var updt = service.update(id, user);
        if(updt.isPresent()){
            return ResponseEntity.ok().body(updt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        return ResponseEntity.status(service.deleteById(id)).build();
    }

    @ExceptionHandler({SQLException.class, DataIntegrityViolationException.class})
    public ResponseEntity databaseError() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,MismatchedInputException.class,ConstraintViolationException.class})
    public ResponseEntity badRequestHandler() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
