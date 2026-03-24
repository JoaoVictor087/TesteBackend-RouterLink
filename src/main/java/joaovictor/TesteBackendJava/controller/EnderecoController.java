package joaovictor.TesteBackendJava.controller;

import joaovictor.TesteBackendJava.DTOs.EnderecoRequestDTO;
import joaovictor.TesteBackendJava.entities.Endereco;
import joaovictor.TesteBackendJava.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/pessoas")
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/{id_pessoa}/enderecos")
    public ResponseEntity<?> adicionarEndereco(@PathVariable Long id_pessoa,@RequestBody EnderecoRequestDTO dto) throws FileNotFoundException {
        Endereco endereco = enderecoService.adicionarEndereco(id_pessoa, dto);
        return ResponseEntity.status(201).body(endereco);
    }

    @PutMapping("/{id_pessoa}/enderecos/{id_endereco}")
    public ResponseEntity<?> atualizarEndereco(@PathVariable Long id_pessoa,
                                               @PathVariable Long id_endereco, @RequestBody EnderecoRequestDTO dto) throws FileNotFoundException {
        Endereco endereco = enderecoService.atualizarEndereco(id_pessoa, id_endereco, dto);
        return ResponseEntity.status(200).body(endereco);
    }

    @DeleteMapping("/{id_pessoa}/enderecos/{id_endereco}")
    public ResponseEntity<?> excluirEndereco(@PathVariable Long id_pessoa, @PathVariable Long id_endereco) throws FileNotFoundException {
        enderecoService.excluirEndereco(id_endereco, id_pessoa);
        return ResponseEntity.noContent().build();
    }

}
