package med.voll.api.repositories;

import med.voll.api.domain.enums.Especialidade;
import med.voll.api.domain.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

/*
    Herdar de uma interface chamada JpaRepository, usando um extends. Entre <>, passaremos dois tipos de objeto.
 */
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("""
                select m from Medicos m
                where
                m.ativo = True
                and
                m.especialidade = :especialidade
                and
                m.id not in(
                        select c.medico_id from Consultas c
                        where
                        c.data = :timestamp
                )
                order by RANDOM()
                limit 1
                """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, Timestamp timestamp);
}
