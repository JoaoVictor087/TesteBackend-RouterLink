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
}
