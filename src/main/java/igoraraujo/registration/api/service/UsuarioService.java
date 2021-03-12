package igoraraujo.registration.api.service;

import igoraraujo.registration.api.model.Usuario;
import igoraraujo.registration.api.repositery.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository repository;


    public ResponseEntity findAll() {
        var it = repository.findAll();
        var users = new ArrayList<Usuario>();
        it.forEach(users::add);
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(users);
    }

    public ResponseEntity findById(String id) {
        int test = Integer.parseInt(id);
        return repository.findById(test)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity findByNome(String nome) {
        var it = repository.findByNomeIgnoreCase(nome);
        if(it.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(it);
    }

    public ResponseEntity findByMatricula(Integer matricula) {
        var it = repository.findByMatricula(matricula);
        if(it.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(it);
    }

    public ResponseEntity deleteById(Integer id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


    public ResponseEntity save(Usuario user) {
        Usuario saved = repository.save(user);
        return ResponseEntity.ok().body(saved);
    }

    public ResponseEntity  update(Integer id, Usuario user){
        return repository.findById(id)
                .map(record -> {
                    record.setNome(user.getNome());
                    record.setSobrenome(user.getSobrenome());
                    record.setMatricula(user.getMatricula());
                    record.setTelefone(user.getTelefone());
                    Usuario updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
}