package joaovictor.TesteBackendJava.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import joaovictor.TesteBackendJava.enums.UF;

@Entity
@Table(name = "enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    private Pessoa pessoa;

    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    @Enumerated(EnumType.STRING)
    private UF uf;
    private String cep;
    private Boolean principal;

    public Endereco(){}

    public Endereco(String complemento, String bairro, String cidade, UF uf, String cep, boolean principal, Integer numero, Long id, String logradouro) {
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.principal = principal;
        this.numero = numero;
        this.id = id;
        this.logradouro = logradouro;
    }

    public Long getId() {
        return id;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public String getCep() {
        return cep;
    }

    public UF getUf() {
        return uf;
    }

    public String getCidade() {
        return cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }
}
