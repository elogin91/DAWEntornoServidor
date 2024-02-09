package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Reserva;

/**
 * Repositorio para la entidad Reserva que proporciona métodos personalizados de consulta.
 */
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
	
	/**
     * Encuentra las reservas de un cliente específico.
     *
     * @param username el nombre de usuario del cliente
     * @return una lista de reservas del cliente especificado
     */
	@Query("select r from Reserva r where r.usuario.username=?1")
	public List<Reserva> findReservasPorCliente(String username);

	/**
     * Encuentra las reservas de un cliente específico para un evento específico.
     *
     * @param username el nombre de usuario del cliente
     * @param idEvento el ID del evento
     * @return una lista de reservas del cliente especificado para el evento especificado
     */
	@Query("select r from Reserva r where r.usuario.username=?1 AND r.evento.idEvento=?2")
	public List<Reserva> findReservasPorClienteYEvento(String username, int idEvento);


	/**
     * Encuentra las reservas para un evento específico.
     *
     * @param idEvento el ID del evento
     * @return una lista de reservas para el evento especificado
     */
	@Query("select r from Reserva r where r.evento.idEvento=?1")
	public List<Reserva> findByIdEvento(int idEvento);
	
	/**
     * Encuentra las reservas pendientes de un cliente específico.
     *
     * @param username el nombre de usuario del cliente
     * @return una lista de reservas pendientes del cliente especificado
     */
	@Query("select r from Reserva r where r.usuario.username=?1 AND r.evento.fechaInicio>current_date")
	public List<Reserva> findReservasPorClientePendientes(String username);
	
	/**
     * Encuentra las reservas caducadas de un cliente específico.
     *
     * @param username el nombre de usuario del cliente
     * @return una lista de reservas caducadas del cliente especificado
     */
	@Query("select r from Reserva r where r.usuario.username=?1 AND r.evento.fechaInicio<current_date")
	public List<Reserva> findReservasPorClienteCaducados(String username);
	
}
