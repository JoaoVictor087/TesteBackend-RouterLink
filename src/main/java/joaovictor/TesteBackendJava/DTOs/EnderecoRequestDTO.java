package joaovictor.TesteBackendJava.DTOs;

import jdk.jfr.BooleanFlag;
import joaovictor.TesteBackendJava.enums.UF;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record EnderecoRequestDTO(
        String logradouro,
        Integer numero,
        String complemento,
        String bairro,
        String cidade,
        String cep,
        UF uf,
        Boolean principal
) {
}

