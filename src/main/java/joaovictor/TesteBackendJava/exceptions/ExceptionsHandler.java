package joaovictor.TesteBackendJava.exceptions;

import joaovictor.TesteBackendJava.DTOs.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(CadastroException.class)
    public ResponseEntity<ErrorResponseDTO> erroCadastro(CadastroException exception){
        ErrorResponseDTO errorDTO =
                new ErrorResponseDTO("CADASTRO INVÁLIDO", exception.getMessage(),
                        "Bad Request", 400 ,LocalDateTime.now());
        return ResponseEntity.status(400).body(errorDTO);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> erroCPF(CPFException exception){
        ErrorResponseDTO errorDTO =
                new ErrorResponseDTO("CPF INVÁLIDO", exception.getMessage(),
                        "Bad Request", 400 ,LocalDateTime.now());
        return ResponseEntity.status(400).body(errorDTO);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> notFound(FileNotFoundException exception){
        ErrorResponseDTO errorDTO =
                new ErrorResponseDTO("Campo não encontrado", exception.getMessage(),
                        "Not FOund", 404 ,LocalDateTime.now());
        return ResponseEntity.status(404).body(errorDTO);
    }

}
