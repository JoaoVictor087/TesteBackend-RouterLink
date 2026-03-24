package joaovictor.TesteBackendJava.service;

import jakarta.transaction.Transactional;
import joaovictor.TesteBackendJava.DTOs.PessoaRequestDTO;
import joaovictor.TesteBackendJava.entities.Pessoa;
import joaovictor.TesteBackendJava.entities.Telefone;
import joaovictor.TesteBackendJava.exceptions.CPFException;
import joaovictor.TesteBackendJava.exceptions.CadastroException;
import joaovictor.TesteBackendJava.reporitory.PessoaRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PessoaService {
    private static final Logger log = LoggerFactory.getLogger(PessoaService.class);
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public Pessoa cadastrarPessoa(PessoaRequestDTO dto) throws CadastroException {
        validarNome(dto.nome());
        if(!(dto.email() == null)){
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
        pessoa.setEnderecos(dto.enderecos());

        pessoaRepository.save(pessoa);
        log.info("Novo usuário cadastrado:{}", pessoa.getNome());
        return pessoa;
    }

    public void validarCPF(String cpf) throws CPFException {
        if(cpf.length() != 11){
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
