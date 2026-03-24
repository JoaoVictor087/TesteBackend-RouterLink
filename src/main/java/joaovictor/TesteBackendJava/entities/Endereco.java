package joaovictor.TesteBackendJava.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import joaovictor.TesteBackendJava.enums.UF;

import java.util.UUID;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private UF uf; ;
    private String cep;
    private boolean principal;

    public Endereco(String complemento, String bairro, String cidade, UF uf, String cep, boolean principal, Integer numero, UUID id, String logradouro) {
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

    public UUID getId() {
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

    public void setId(UUID id) {
        this.id = id;
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
}
