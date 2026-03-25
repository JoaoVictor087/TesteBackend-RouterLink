package joaovictor.TesteBackendJava.service;

import jakarta.transaction.Transactional;
import joaovictor.TesteBackendJava.DTOs.EnderecoRequestDTO;
import joaovictor.TesteBackendJava.entities.Endereco;
import joaovictor.TesteBackendJava.entities.Pessoa;
import joaovictor.TesteBackendJava.exceptions.EnderecoException;
import joaovictor.TesteBackendJava.repository.EnderecoRepository;
import joaovictor.TesteBackendJava.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private static final Logger log = LoggerFactory.getLogger(EnderecoService.class);
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;

    }

    @Transactional
    public Endereco adicionarEndereco(Long id_usuario, EnderecoRequestDTO dto) throws FileNotFoundException {
        if (Boolean.TRUE.equals(dto.principal())){
            verificaEnderecoPrincipal(id_usuario, dto.principal(), null);
        }
        log.info(String.valueOf(dto));


        Optional<Pessoa> pessoa = pessoaRepository.findById(id_usuario);
        if (pessoa.isEmpty()) {
            throw new FileNotFoundException("Usuário não encontrado");
        }
        Endereco endereco = new Endereco();
        endereco.setPessoa(pessoa.get());
        endereco.setLogradouro(dto.logradouro());
        endereco.setNumero(dto.numero());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setComplemento(dto.complemento());
        endereco.setUf(dto.uf());
        endereco.setCep(dto.cep());
        endereco.setPrincipal(Boolean.TRUE.equals(dto.principal()));

        enderecoRepository.save(endereco);
        log.info("Endereço Adicionado: {}", endereco);
        return endereco;
    }

    @Transactional
    public Endereco atualizarEndereco(Long id_pessoa, Long id_endereco, EnderecoRequestDTO dto) throws FileNotFoundException {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id_pessoa);
        if (pessoa.isEmpty()) {
            throw new FileNotFoundException("Pessoa não encontrada");
        }

        Optional<Endereco> endereco = enderecoRepository.findById(id_endereco);
        if (endereco.isEmpty()) {
            throw new FileNotFoundException("Endereço não encontrado");
        }

        verificaEnderecoPrincipal(id_pessoa, Boolean.TRUE.equals(dto.principal()), id_endereco);

        if (dto.cep() != null) {
            endereco.get().setCep(dto.cep());
        }
        if (dto.logradouro() != null) {
            endereco.get().setLogradouro(dto.logradouro());
        }
        if (dto.numero() != null) {
            endereco.get().setNumero(dto.numero());
        }
        if (dto.bairro() != null) {
            endereco.get().setBairro(dto.bairro());
        }
        if (dto.cidade() != null) {
            endereco.get().setCidade(dto.cidade());
        }
        if (dto.complemento() != null) {
            endereco.get().setComplemento(dto.complemento());
        }
        if (dto.uf() != null) {
            endereco.get().setUf(dto.uf());
        }

        if (dto.principal() != null) {
            endereco.get().setPrincipal(dto.principal());
        }
        enderecoRepository.save(endereco.get());
        log.info("Endereço atualizado: {}", endereco.get());

        return endereco.get();
    }

    @Transactional
    public void excluirEndereco(Long id_endereco, Long id_pessoa) throws FileNotFoundException {
        Optional<Pessoa>pessoa = pessoaRepository.findById(id_pessoa);
        if(pessoa.isEmpty()){
            throw new FileNotFoundException("Usuário não encontrado");
        }
        Optional<Endereco>endereco = enderecoRepository.findById(id_endereco);
        if(endereco.isEmpty()){
            throw new FileNotFoundException("Endereço não encontrado");
        }
        enderecoRepository.delete(endereco.get());
        log.info("Endereço excluido com sucesso");
    }

    public void verificaEnderecoPrincipal(Long id_pessoa, Boolean endereco_boolean, Long id_endereco) throws FileNotFoundException {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id_pessoa);

        if (pessoa.isEmpty()){
            throw new FileNotFoundException("Usuario não encontrado");
        }
        List<Endereco> enderecoList = pessoa.get().getEnderecos();
        if (enderecoList.isEmpty()){
            return;
        }

        int i = 0;
        for (Endereco endereco: enderecoList){
            if(endereco.isPrincipal() && !endereco.getId().equals(id_endereco)){
                i++;
            }
        }


        if(i>=1 && endereco_boolean ){
            throw new EnderecoException("Já possui um endereço principal para o usuário");
        }
    }
}
