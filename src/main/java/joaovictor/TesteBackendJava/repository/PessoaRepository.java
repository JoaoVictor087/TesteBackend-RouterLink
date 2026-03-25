package joaovictor.TesteBackendJava.repository;

import joaovictor.TesteBackendJava.entities.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByCpf(String cpf);
    Optional<Pessoa> findById(Long id);
    Page<Pessoa> findAll(Pageable page);
}
