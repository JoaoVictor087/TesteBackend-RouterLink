package joaovictor.TesteBackendJava.controller;

import joaovictor.TesteBackendJava.DTOs.ErrorResponseDTO;
import joaovictor.TesteBackendJava.DTOs.PessoaRequestDTO;
import joaovictor.TesteBackendJava.DTOs.PessoaResponseDTO;
import joaovictor.TesteBackendJava.entities.Pessoa;
import joaovictor.TesteBackendJava.exceptions.CadastroException;
import joaovictor.TesteBackendJava.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody PessoaRequestDTO dto) {
        Pessoa pessoa = pessoaService.cadastrarPessoa(dto);
        PessoaResponseDTO response =
                new PessoaResponseDTO(pessoa.getId(), pessoa.getNome(), pessoa.getEmail());
        return ResponseEntity.status(201).body(response);
    }
}
