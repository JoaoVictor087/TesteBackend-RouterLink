package joaovictor.TesteBackendJava.reporitory;

import joaovictor.TesteBackendJava.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
