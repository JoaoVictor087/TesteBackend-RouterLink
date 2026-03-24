package joaovictor.TesteBackendJava.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    @OneToMany(mappedBy = "pessoa")
    private List<Telefone> telefones;
    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> enderecos;

    public Pessoa(){
    }

    public Pessoa(String email, String cpf, String nome, UUID id, LocalDate dataNascimento, List<Telefone> telefones, List<Endereco> enderecos) {
        this.email = email;
        this.cpf = cpf;
        this.nome = nome;
        this.id = id;
        this.dataNascimento = dataNascimento;
        this.telefones = telefones;
        this.enderecos = enderecos;
    }

    public UUID getId() {
        return id;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
