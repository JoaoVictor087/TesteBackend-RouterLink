package joaovictor.TesteBackendJava.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private LocalDateTime dataNascimento;
    private List<String> telefones;
    private List<Endereco> enderecos;

    public Pessoa(){
    }

    public Pessoa(String email, String cpf, String nome, UUID id, LocalDateTime dataNascimento, List<String> telefones, List<Endereco> enderecos) {
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

    public List<String> getTelefones() {
        return telefones;
    }

    public LocalDateTime getDataNascimento() {
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

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
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
