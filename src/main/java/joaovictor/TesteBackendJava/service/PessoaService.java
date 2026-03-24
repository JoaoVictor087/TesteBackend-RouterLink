package joaovictor.TesteBackendJava.service;

import jakarta.transaction.Transactional;
import joaovictor.TesteBackendJava.DTOs.PessoaRequestDTO;
import joaovictor.TesteBackendJava.entities.Pessoa;
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
        if (dto.nome() == null || dto.nome().isEmpty()) {
            throw new CadastroException("Nome não pode ser vazio");
        }
        validarCPF(dto.cpf());
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setCpf(dto.cpf());
        pessoa.setEmail(dto.email());
        pessoa.setDataNascimento(dto.dataNascimento());
        pessoa.setTelefones(dto.telefones());
        pessoa.setEnderecos(dto.enderecos());

        pessoaRepository.save(pessoa);
        log.info("Novo usuário cadastrado: ${}", pessoa.getNome());
        return pessoa;
    }

    public void validarCPF(String cpf) throws CPFException {
        if (cpf == null || cpf.isEmpty()) {
            log.info("CPF não pode ser nulo ou vazio");
            throw new CPFException("CPF obrigatório");
        }
        if (pessoaRepository.existsByCpf(cpf)) {
            log.info("CPF já existe no banco de dados: ${}", cpf);
            throw new CPFException("CPF já existe no banco de dados");
        }
    }
}
