package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entitis.Reserva;

public interface ReservaDao {

	Reserva altaReserva (Reserva reserva);
	Reserva modificarReserva (Reserva reserva);
	Reserva cancelarReserva (Reserva reserva);
	Reserva buscarUnaReserva (Reserva reserva);
	List<Reserva> buscarTodasReservasPorCliente (String Username);
	
}
