package joaovictor.TesteBackendJava.service;

import jakarta.transaction.Transactional;
import joaovictor.TesteBackendJava.DTOs.PessoaRequestDTO;
import joaovictor.TesteBackendJava.DTOs.PutPessoaRequestDTO;
import joaovictor.TesteBackendJava.entities.Endereco;
import joaovictor.TesteBackendJava.entities.Pessoa;
import joaovictor.TesteBackendJava.entities.Telefone;
import joaovictor.TesteBackendJava.exceptions.CPFException;
import joaovictor.TesteBackendJava.exceptions.CadastroException;
import joaovictor.TesteBackendJava.reporitory.EnderecoRepository;
import joaovictor.TesteBackendJava.reporitory.PessoaRepository;
import joaovictor.TesteBackendJava.reporitory.TelefoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private static final Logger log = LoggerFactory.getLogger(PessoaService.class);
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

    public PessoaService(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository, TelefoneRepository telefoneRepository) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
        this.telefoneRepository = telefoneRepository;
    }

    @Transactional
    public Pessoa cadastrarPessoa(PessoaRequestDTO dto) throws CadastroException {
        log.info(String.valueOf(dto));
        validarNome(dto.nome());
        if (!(dto.email() == null)) {
            validarEmail(dto.email());
        }
        validarCPF(dto.cpf());

        if (dto.telefones() != null) {
            for (Telefone telefone : dto.telefones()) {
                validarNumeroTelefone(telefone.getNumeroTelefone());
            }
        }
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setCpf(dto.cpf());
        pessoa.setEmail(dto.email());
        pessoa.setDataNascimento(dto.dataNascimento());
        pessoa.setTelefones(dto.telefones());
        pessoaRepository.save(pessoa);
        log.info("Novo usuário cadastrado:{}", pessoa.getNome());

        if (dto.enderecos() != null) {
            for (int i = 0; i < dto.enderecos().size(); i++) {
                Endereco endereco = new Endereco();
                endereco.setBairro(dto.enderecos().get(i).getBairro());
                endereco.setLogradouro(dto.enderecos().get(i).getLogradouro());
                endereco.setCep(dto.enderecos().get(i).getCep());
                endereco.setCidade(dto.enderecos().get(i).getCidade());
                endereco.setComplemento(dto.enderecos().get(i).getComplemento());
                endereco.setUf(dto.enderecos().get(i).getUf());
                endereco.setNumero(dto.enderecos().get(i).getNumero());
                endereco.setPrincipal(dto.enderecos().get(i).isPrincipal());
                endereco.setPessoa(pessoa);

                log.info("Endereco criado {}", endereco);
                enderecoRepository.save(endereco);
            }
        }

        if(dto.telefones() != null){
            for (int i = 0; i < dto.telefones().size(); i++) {
                Telefone telefone = new Telefone();
                telefone.setNumeroTelefone(dto.telefones().get(i).getNumeroTelefone());
                telefone.setPessoa(pessoa);

                log.info("Telefone Criado {}", telefone);
                telefoneRepository.save(telefone);

            }
        }
        return pessoa;
    }

    @Transactional
    public Pessoa alterarDadosPessoa(PutPessoaRequestDTO dto, Long idPessoa) throws FileNotFoundException {
        Optional<Pessoa> pessoaOptional = pessoaRepository.getById(idPessoa);
        if (pessoaOptional.isEmpty()) {
            throw new FileNotFoundException("Pessoa não existe no banco de dados");
        }
        Pessoa pessoa = pessoaOptional.get();
        if (dto.nome() != null && !dto.nome().isEmpty()) {
            pessoa.setNome(dto.nome());
        }
        if (dto.cpf() != null && !dto.cpf().isEmpty()) {
            pessoa.setCpf(dto.cpf());
        }
        if (dto.email() != null && !dto.email().isEmpty()) {
            pessoa.setEmail(dto.email());
        }
        if (dto.dataNascimento() != null) {
            pessoa.setDataNascimento(dto.dataNascimento());
        }
        if (dto.telefones() != null) {
            for (Telefone telefone : dto.telefones()) {
                List<Telefone> listaDeTelefone = new ArrayList<>();
                listaDeTelefone.add(telefone);
                pessoa.setTelefones(listaDeTelefone);
            }
        }
        if (dto.enderecos() != null && !dto.enderecos().isEmpty()) {
            pessoa.setEnderecos(dto.enderecos());
        }

        pessoaRepository.save(pessoa);
        return pessoa;
    }

    public Page<Pessoa> buscarTodos(Pageable pageable){
        Page<Pessoa>pessoas = pessoaRepository.findAll(pageable);
        return pessoas;
    }

    public Pessoa buscarPessoa(Long idPessoa) throws FileNotFoundException{
        Optional<Pessoa> pessoa = pessoaRepository.getById(idPessoa);
        if (pessoa.isEmpty()){
            throw new FileNotFoundException("Usuário não encontrado");
        }
        return pessoa.get();
    }

    public void validarCPF(String cpf) throws CPFException {
        if (cpf.length() != 11) {
            throw new CPFException("CPF deve conter 11 dígitos");
        }

        if (cpf == null || cpf.isEmpty()) {
            log.info("CPF não pode ser nulo ou vazio");
            throw new CPFException("CPF obrigatório");
        }

        if (pessoaRepository.existsByCpf(cpf)) {
            log.info("CPF já existe no banco de dados: ${}", cpf);
            throw new CPFException("CPF já existe no banco de dados");
        }
    }

    public void validarNome(String nome) throws CadastroException {
        if (nome == null || nome.isEmpty()) {
            throw new CadastroException("Nome não pode ser vazio");
        }
    }

    public void validarNumeroTelefone(String numeroTelefone) throws CadastroException {
        if (numeroTelefone.length() != 11) {
            throw new CadastroException("Formato de número de telefone inválido");
        }
    }

    public void validarEmail(String email) {
        if (!email.contains("@")) {
            throw new CadastroException("Deve ser fornecido um email válido");
        }
    }

}
