package joaovictor.TesteBackendJava.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import joaovictor.TesteBackendJava.entities.Endereco;
import joaovictor.TesteBackendJava.entities.Telefone;

import java.time.LocalDate;
import java.util.List;

public record PessoaRequestDTO(
        @NotNull
        String nome,
        @NotNull
        String cpf,
        @Email
        String email,
        LocalDate dataNascimento,
        List<Telefone> telefones,
        List<Endereco> enderecos
) {
}
