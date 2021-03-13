package igoraraujo.registration.api.repositery;

import igoraraujo.registration.api.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>  {
    ArrayList<Usuario> findByNomeIgnoreCase(String nome);
    ArrayList<Usuario> findByMatricula(Integer matricula);
}

