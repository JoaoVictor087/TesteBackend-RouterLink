package joaovictor.TesteBackendJava.DTOs;

import java.util.UUID;

public record PessoaResponseDTO(
    Long id,
    String nome,
    String email
){}