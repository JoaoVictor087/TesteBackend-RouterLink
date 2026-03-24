package joaovictor.TesteBackendJava.DTOs;

import joaovictor.TesteBackendJava.entities.Endereco;

import java.time.LocalDateTime;
import java.util.List;

public record PessoaRequestDTO(
        String nome,
        String cpf,
        String email,
        LocalDateTime dataNascimento,
        List<String> telefones,
        List<Endereco> enderecos
) {
}
