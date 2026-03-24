package joaovictor.TesteBackendJava.controller;

import jakarta.validation.Valid;
import joaovictor.TesteBackendJava.DTOs.PessoaRequestDTO;
import joaovictor.TesteBackendJava.DTOs.PessoaResponseDTO;
import joaovictor.TesteBackendJava.DTOs.PutPessoaRequestDTO;
import joaovictor.TesteBackendJava.entities.Pessoa;
import joaovictor.TesteBackendJava.service.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

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

    @PutMapping("/{id_pessoa}")
    public ResponseEntity<?>atualizarUsuario(@RequestBody @Valid PutPessoaRequestDTO dto, @PathVariable Long id_pessoa)
            throws FileNotFoundException {
        Pessoa pessoa = pessoaService.alterarDadosPessoa(dto, id_pessoa);
        PessoaResponseDTO response =
                new PessoaResponseDTO(pessoa.getId(), pessoa.getNome(), pessoa.getEmail());
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos(Pageable pageable){
        return ResponseEntity.status(200).body(pessoaService.buscarTodos(pageable));
    }

    @GetMapping("/{id_pessoa}")
    public ResponseEntity<?> buscarPessoa(@PathVariable Long id_pessoa) throws FileNotFoundException {
        return ResponseEntity.status(200).body(pessoaService.buscarPessoa(id_pessoa));
    }
}
