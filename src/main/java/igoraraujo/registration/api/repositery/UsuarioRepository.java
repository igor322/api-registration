package igoraraujo.registration.api.repositery;

import igoraraujo.registration.api.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>  {
    List<Usuario> findByNome(String nome);
    List<Usuario> findByMatricula(Integer matricula);
}

