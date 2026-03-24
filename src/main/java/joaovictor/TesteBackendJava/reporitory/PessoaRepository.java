package joaovictor.TesteBackendJava.reporitory;

import joaovictor.TesteBackendJava.entities.Pessoa;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
    boolean existsByCpf(String cpf);
}
