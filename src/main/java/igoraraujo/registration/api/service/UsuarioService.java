package igoraraujo.registration.api.service;

import igoraraujo.registration.api.model.Usuario;
import igoraraujo.registration.api.repositery.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository repository;


    public ArrayList<Usuario> findAll() {
        var it = repository.findAllByOrderById();
        var users = new ArrayList<Usuario>();
        it.forEach(users::add);
        return users;
    }

    public Optional<Usuario> findById(Integer id){
        return repository.findById(id);
    }

    public ArrayList<Usuario> findByNome(String nome) {
        return repository.findByNomeIgnoreCase(nome);
    }

    public ArrayList<Usuario> findByMatricula(Integer matricula) {
        return repository.findByMatricula(matricula);
    }

    public void save(Usuario user) {
        repository.save(user);
    }

    public Optional<Object> update(Integer id, Usuario user){
        return repository.findById(id)
                .map(record -> {
                    record.setNome(user.getNome());
                    record.setSobrenome(user.getSobrenome());
                    record.setMatricula(user.getMatricula());
                    record.setTelefone(user.getTelefone());
                    Usuario updated = repository.save(record);
                    return updated;
                });
    }

    public HttpStatus deleteById(Integer id){
        return repository.findById(id)
                .map(record -> {
                    repository.delete(record);
                    return HttpStatus.OK;
                }).orElse(HttpStatus.NOT_FOUND);
    }
}