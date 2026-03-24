package joaovictor.TesteBackendJava.controller;

import joaovictor.TesteBackendJava.DTOs.EnderecoRequestDTO;
import joaovictor.TesteBackendJava.entities.Endereco;
import joaovictor.TesteBackendJava.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/pessoas/{id_pessoa}/enderecos")
    public ResponseEntity<?> adicionarEndereco(@PathVariable Long id_pessoa,@RequestBody EnderecoRequestDTO dto) throws FileNotFoundException {
        Endereco endereco =enderecoService.adicionarEndereco(id_pessoa, dto);
        return ResponseEntity.status(201).body(endereco);
    }
}
