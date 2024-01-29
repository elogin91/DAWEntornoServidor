package eventos.modelo.entitis;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * The persistent class for the reservas database table.
 * 
 */
@Entity
@Table(name = "reservas")
@NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
public class Reserva implements Serializable {
	private static final int RESERVAS_MAXIMAS_POR_CLIENTE = 10;

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESERVA")
	private int idReserva;

	private int cantidad;

	private String observaciones;

	@Column(name = "PRECIO_VENTA")
	private BigDecimal precioVenta;

	// uni-directional many-to-one association to Evento
	@ManyToOne
	@JoinColumn(name = "ID_EVENTO")
	private Evento evento;

	// uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "USERNAME")
	private Usuario usuario;

	public Reserva() {
	}

	public int getIdReserva() {
		return this.idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public BigDecimal getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Evento getEvento() {
		return this.evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean validarCantidad(int reservasRealizadas) {
		return validarCantidadMaxima() && validarAforoDisponible(reservasRealizadas);
	}

	private Boolean validarAforoDisponible(int reservasRealizadas) {
		int aforoDisponible = this.getEvento().getAforoMaximo() - reservasRealizadas;
		return cantidad <= aforoDisponible;
	}

	private Boolean validarCantidadMaxima() {
		return this.cantidad <= RESERVAS_MAXIMAS_POR_CLIENTE;
	}
}