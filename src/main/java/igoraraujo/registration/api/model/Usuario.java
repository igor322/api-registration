package igoraraujo.registration.api.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"matricula"})})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Name é obrigatorio")
    @Size(min = 3, max = 50, message = "Nome deve conter entre 3 e 50 caracteres")
    private String nome;

    @NotBlank(message = "Sobrenome é obrigatorio")
    @Size(min = 3, max = 100, message = "Sobrenome deve conter entre 3 e 100 caracteres")
    private String sobrenome;

    @Column(unique = true)
    @NotNull(message = "Matricula deve ser um numero valido")
    @Min(value = 100, message = "Matricula deve ser maior ou igual a 100")
    private Integer matricula;

    @NotEmpty(message = "Deve conter ao menos um array vazio")
    private String[] telefone;

    public Usuario(@NotBlank(message = "Name é obrigatorio") @Size(min = 3, max = 50, message = "Nome deve conter entre 3 e 50 caracteres") String nome, @NotBlank(message = "Sobrenome é obrigatorio") @Size(min = 3, max = 100, message = "Sobrenome deve conter entre 3 e 100 caracteres") String sobrenome, @NotNull(message = "Matricula deve ser um numero valido") @Min(value = 100, message = "Matricula deve ser maior ou igual a 100") Integer matricula, @NotEmpty(message = "Deve conter ao menos um array vazio") String[] telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.matricula = matricula;
        this.telefone = telefone;
    }

    public Usuario(){

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String[] getTelefone() {
        return telefone;
    }

    public void setTelefone(String[] telefone) {
        this.telefone = telefone;
    }
}
