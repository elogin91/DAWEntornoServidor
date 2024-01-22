package eventos.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository <Reserva, Integer>{

}
