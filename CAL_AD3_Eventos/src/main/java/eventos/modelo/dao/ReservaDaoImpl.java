package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Reserva;
import eventos.modelo.repository.ReservaRepository;

@Repository
public class ReservaDaoImpl implements ReservaDao {

	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public Reserva altaReserva(Reserva reserva) {

		List<Reserva> reservaAntigua = reservaRepository
				.findReservasPorClienteYEvento(reserva.getUsuario().getUsername(), reserva.getEvento().getIdEvento());
		//Funciona con una nueva reserva, cuando la antigua es null ?
		if (reserva.validarCantidad(reservaRepository.countByEvento(reserva.getEvento()),
				reservaAntigua.stream().mapToInt(it -> it.getCantidad()).sum())) {
			try {
				return reservaRepository.save(reserva);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		return null;
	}

	@Override
	public Reserva modificarReserva(Reserva reserva) {

		List<Reserva> reservaAntigua = reservaRepository
				.findReservasPorClienteYEvento(reserva.getUsuario().getUsername(), reserva.getEvento().getIdEvento());
		if (reserva.validarCantidad(reservaRepository.countByEvento(reserva.getEvento()),
				reservaAntigua.stream().mapToInt(it -> it.getCantidad()).sum())) {
			try {
				return reservaRepository.save(reserva);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		return null;
	}

	@Override
	public Reserva cancelarReserva(Reserva reserva) {
		try {
			reservaRepository.delete(reserva);
			return reserva;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Reserva buscarUnaReserva(int idReserva) {
		return reservaRepository.findById(idReserva).orElse(null);
	}

	@Override
	public List<Reserva> buscarReservasPorCliente(String username) {
		return reservaRepository.findReservasPorCliente(username);
	}

	@Override
	public List<Reserva> buscarReservasPorClienteYEvento(String username, int idEvento) {
		return reservaRepository.findReservasPorClienteYEvento(username, idEvento);
	}

	@Override
	public List<Reserva> buscarReservasPorEvento(int idEvento) {
		return reservaRepository.findByIdEvento(idEvento);
	}

}
