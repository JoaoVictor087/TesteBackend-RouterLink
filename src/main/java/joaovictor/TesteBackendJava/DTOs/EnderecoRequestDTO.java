package joaovictor.TesteBackendJava.DTOs;

import joaovictor.TesteBackendJava.enums.UF;

public record EnderecoRequestDTO(
        String Logradouro,
        Integer numero,
        String complemento,
        String bairro,
        String cidade,
        String cep,
        UF uf,
        boolean principal
) {
}

