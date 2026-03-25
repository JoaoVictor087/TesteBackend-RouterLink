package joaovictor.TesteBackendJava.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones;
    @OneToMany(mappedBy = "pessoa",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

    public Pessoa(){
    }

    public Pessoa(String email, String cpf, String nome, Long id, LocalDate dataNascimento, List<Telefone> telefones, List<Endereco> enderecos) {
        this.email = email;
        this.cpf = cpf;
        this.nome = nome;
        this.id = id;
        this.dataNascimento = dataNascimento;
        this.telefones = telefones;
        this.enderecos = enderecos;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public String getNome() {
        return nome;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }


}
