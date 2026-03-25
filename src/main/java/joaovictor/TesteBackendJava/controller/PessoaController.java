package joaovictor.TesteBackendJava.controller;

import jakarta.validation.Valid;
import joaovictor.TesteBackendJava.DTOs.PessoaRequestDTO;
import joaovictor.TesteBackendJava.DTOs.PutPessoaRequestDTO;
import joaovictor.TesteBackendJava.entities.Pessoa;
import joaovictor.TesteBackendJava.service.PessoaService;
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
        return ResponseEntity.status(201).body(pessoa);
    }

    @PutMapping("/{id_pessoa}")
    public ResponseEntity<?>atualizarUsuario(@RequestBody @Valid PutPessoaRequestDTO dto, @PathVariable Long id_pessoa)
            throws FileNotFoundException {
        Pessoa pessoa = pessoaService.alterarDadosPessoa(dto, id_pessoa);
        return ResponseEntity.status(200).body(pessoa);
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos(Pageable pageable){
        return ResponseEntity.status(200).body(pessoaService.buscarTodos(pageable));
    }

    @GetMapping("/{id_pessoa}")
    public ResponseEntity<?> buscarPessoa(@PathVariable Long id_pessoa) throws FileNotFoundException {
        return ResponseEntity.status(200).body(pessoaService.buscarPessoa(id_pessoa));
    }

    @DeleteMapping("/{id_pessoa}")
    public ResponseEntity<?>excluirPorId(@PathVariable Long id_pessoa) throws FileNotFoundException {
        pessoaService.excluirPessoa(id_pessoa);
        return ResponseEntity.status(204).build();
    }



}
