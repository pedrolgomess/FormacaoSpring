package med.voll.api.repositories;

import med.voll.api.domain.enums.Especialidade;
import med.voll.api.domain.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/*
    Herdar de uma interface chamada JpaRepository, usando um extends. Entre <>, passaremos dois tipos de objeto.
 */
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("""
                select m from Medico m
                where
                m.ativo = true
                and
                m.especialidade = :especialidade
                and
                m.id not in(
                        select c.medico.id from Consulta c
                        where
                        c.localDateTime = :timestamp
                )
                order by RANDOM()
                limit 1
                """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime timestamp);
}
