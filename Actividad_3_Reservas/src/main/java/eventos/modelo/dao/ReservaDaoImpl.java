package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Reserva;
import eventos.modelo.repository.ReservaRepository;

@Repository
public class ReservaDaoImpl implements ReservaDao {
	private static final int RESERVAS_MAXIMAS_POR_CLIENTE = 10;
	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public Reserva altaReserva(Reserva reserva) {

		if (reserva.getCantidad() <= RESERVAS_MAXIMAS_POR_CLIENTE && tieneAforo(reserva.getEvento(), reserva.getCantidad())){
			try {
				return reservaRepository.save(reserva);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
		}
		return null;
		
	}

	private int calcularCantidadReservaEvento  (int idEvento) {
		List<Reserva> reservasDelEvento= reservaRepository.findByIdEvento(idEvento);
		int cantidadReservasEvento = reservasDelEvento.stream().mapToInt(it -> it.getCantidad()).sum();
		return cantidadReservasEvento;
	}
	
	
	private boolean tieneAforo(Evento evento, int nuevasReservas) {
		int cantidadDeReservasAntiguas = calcularCantidadReservaEvento(evento.getIdEvento());
		int aforoMaximo = evento.getAforoMaximo();
		int aforoDisponible = aforoMaximo - cantidadDeReservasAntiguas ;
		
		return cantidadDeReservasAntiguas <= aforoMaximo && nuevasReservas <= aforoDisponible;
	}
	
	@Override
	public Reserva modificarReserva(Reserva reservaAntigua, int cantidadNueva) {
		if(cantidadNueva <= RESERVAS_MAXIMAS_POR_CLIENTE && tieneAforo(reservaAntigua.getEvento(), cantidadNueva - reservaAntigua.getCantidad()) ) {
			try {
				reservaAntigua.setCantidad(cantidadNueva);
				return reservaRepository.save(reservaAntigua);
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

	@Override
	public List<Reserva> buscarReservasPorClientePendientes(String username) {
		
		return reservaRepository.findReservasPorClientePendientes(username);
	}

	@Override
	public List<Reserva> buscarReservasPorClienteCaducadas(String username) {
		// TODO Auto-generated method stub
		return reservaRepository.findReservasPorClienteCaducados(username);
	}

}
