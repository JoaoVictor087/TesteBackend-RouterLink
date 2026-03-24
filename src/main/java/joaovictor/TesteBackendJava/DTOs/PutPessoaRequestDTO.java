package joaovictor.TesteBackendJava.DTOs;

import jakarta.validation.constraints.Email;
import joaovictor.TesteBackendJava.entities.Endereco;
import joaovictor.TesteBackendJava.entities.Telefone;

import java.time.LocalDate;
import java.util.List;

public record PutPessoaRequestDTO(
        String nome,
        String cpf,
        @Email
        String email,
        LocalDate dataNascimento,
        List<Telefone> telefones,
        List<Endereco> enderecos
) {
}
