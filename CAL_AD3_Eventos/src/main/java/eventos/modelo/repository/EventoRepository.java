package eventos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Evento;

@Repository
public interface EventoRepository extends JpaRepository <Evento, Integer>{

}
