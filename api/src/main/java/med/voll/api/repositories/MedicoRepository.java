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
                select m from Medicos m
                where
                m.ativo = True
                and
                m.especialidade = 'CARDIOLOGIA'
                and
                m.id not in(
                        select c.medico_id from Consultas c
                        where
                        c.data = '20240105T10:00'
                )
                order by RANDOM()
                limit 1
                """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime timestamp);
}
