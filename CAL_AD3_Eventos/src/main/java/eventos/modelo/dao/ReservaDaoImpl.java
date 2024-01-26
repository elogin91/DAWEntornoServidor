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
		if (reserva.validarCantidad(reservaRepository.countByEvento(reserva.getEvento()))) {
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
		if (reserva.validarCantidad(reservaRepository.countByEvento(reserva.getEvento()))) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reserva buscarUnaReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		return null;
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
