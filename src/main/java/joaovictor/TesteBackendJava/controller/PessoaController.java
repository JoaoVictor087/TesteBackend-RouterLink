package joaovictor.TesteBackendJava.controller;

import jakarta.validation.Valid;
import joaovictor.TesteBackendJava.DTOs.ErrorResponseDTO;
import joaovictor.TesteBackendJava.DTOs.PessoaRequestDTO;
import joaovictor.TesteBackendJava.DTOs.PessoaResponseDTO;
import joaovictor.TesteBackendJava.entities.Pessoa;
import joaovictor.TesteBackendJava.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid PessoaRequestDTO dto) {
        Pessoa pessoa = pessoaService.cadastrarPessoa(dto);
        PessoaResponseDTO response =
                new PessoaResponseDTO(pessoa.getId(), pessoa.getNome(), pessoa.getEmail());
        return ResponseEntity.status(201).body(response);
    }
}
