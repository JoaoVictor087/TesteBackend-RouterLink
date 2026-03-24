package joaovictor.TesteBackendJava.reporitory;

import joaovictor.TesteBackendJava.entities.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
    boolean existsByCpf(String cpf);
    Optional<Pessoa> getById(Long id);
    Page<Pessoa> findAll(Pageable page);
}
