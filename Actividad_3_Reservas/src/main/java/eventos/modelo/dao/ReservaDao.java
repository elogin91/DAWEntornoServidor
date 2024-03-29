package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entitis.Reserva;

public interface ReservaDao {

	Reserva altaReserva(Reserva reserva);

	Reserva modificarReserva(Reserva reserva, int cantidadNueva);

	Reserva cancelarReserva(Reserva reserva);

	Reserva buscarUnaReserva(int idEvent);

	List<Reserva> buscarReservasPorCliente(String username);

	List<Reserva> buscarReservasPorClienteYEvento(String username, int idEvento);

	List<Reserva> buscarReservasPorEvento(int idEvento);

	List<Reserva> buscarReservasPorClientePendientes(String username);

	List<Reserva> buscarReservasPorClienteCaducadas(String username);
}
