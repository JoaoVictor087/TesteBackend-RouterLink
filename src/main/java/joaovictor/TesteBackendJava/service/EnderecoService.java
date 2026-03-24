package joaovictor.TesteBackendJava.service;

import jakarta.transaction.Transactional;
import joaovictor.TesteBackendJava.DTOs.EnderecoRequestDTO;
import joaovictor.TesteBackendJava.entities.Endereco;
import joaovictor.TesteBackendJava.entities.Pessoa;
import joaovictor.TesteBackendJava.reporitory.EnderecoRepository;
import joaovictor.TesteBackendJava.reporitory.PessoaRepository;
import joaovictor.TesteBackendJava.reporitory.TelefoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Optional;

@Service
public class EnderecoService {
    private static final Logger log = LoggerFactory.getLogger(PessoaService.class);
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

    public EnderecoService(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository, TelefoneRepository telefoneRepository) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
        this.telefoneRepository = telefoneRepository;
    }

    @Transactional
    public Endereco adicionarEndereco(Long id_usuario, EnderecoRequestDTO dto) throws FileNotFoundException{
        log.info(String.valueOf(dto));
        Optional<Pessoa> pessoa = pessoaRepository.findById(id_usuario);
        if (pessoa.isEmpty()){
            throw new FileNotFoundException("Usuário não encontrado");
        }
        Endereco endereco = new Endereco();
        endereco.setPessoa(pessoa.get());
        endereco.setLogradouro(dto.Logradouro());
        endereco.setNumero(dto.numero());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setComplemento(dto.complemento());
        endereco.setUf(dto.uf());
        endereco.setCep(dto.cep());
        endereco.setPrincipal(dto.principal());

        enderecoRepository.save(endereco);
        log.info("Endereço Adicionado: {}", endereco);
        return endereco;
    }
}
