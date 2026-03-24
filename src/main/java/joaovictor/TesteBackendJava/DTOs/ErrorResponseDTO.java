package joaovictor.TesteBackendJava.DTOs;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        String nomeErro,
        String descricaoErro,
        String statusErro,
        Integer codigoErro,
        LocalDateTime horaErro
) {
}
