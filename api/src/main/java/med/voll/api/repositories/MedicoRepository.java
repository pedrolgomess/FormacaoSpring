package med.voll.api.repositories;

import med.voll.api.domain.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    Herdar de uma interface chamada JpaRepository, usando um extends. Entre <>, passaremos dois tipos de objeto.
 */
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
