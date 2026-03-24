package joaovictor.TesteBackendJava.DTOs;

import joaovictor.TesteBackendJava.entities.Endereco;
import joaovictor.TesteBackendJava.entities.Telefone;

import java.time.LocalDateTime;
import java.util.List;

public record PessoaRequestDTO(
        String nome,
        String cpf,
        String email,
        LocalDateTime dataNascimento,
        List<Telefone> telefones,
        List<Endereco> enderecos
) {
}
