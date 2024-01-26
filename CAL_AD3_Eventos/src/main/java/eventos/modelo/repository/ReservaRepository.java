package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Reserva;
import eventos.modelo.entitis.Evento;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
	@Query("select r from Reserva r where r.usuario.username=?1")
	public List<Reserva> findReservasPorCliente(String username);

	@Query("select r from Reserva r where r.usuario.username=?1 AND r.evento.idEvento=?2")
	public List<Reserva> findReservasPorClienteYEvento(String username, int idEvento);

	// TODO del me
	public int countByEvento(Evento evento);

	@Query("select r from Reserva r where r.evento.idEvento=?1")
	public List<Reserva> findByIdEvento(int idEvento);
}
