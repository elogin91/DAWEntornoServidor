package vacantes.modelo.entidades;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the solicitudes database table.
 * 
 */
@Entity
@Table(name="solicitudes")
public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_solicitud")
	private int idSolicitud;

	private String archivo;

	private String comentarios;
	private int estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="username")
	private Usuario usuario;

	//uni-directional many-to-one association to Vacante
	@ManyToOne
	@JoinColumn(name="id_Vacante")
	private Vacante vacante;

	public Solicitud() {
	}

	public int getIdSolicitud() {
		return this.idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getArchivo() {
		return this.archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vacante getVacante() {
		return this.vacante;
	}

	public void setVacante(Vacante vacante) {
		this.vacante = vacante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSolicitud;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Solicitud))
			return false;
		Solicitud other = (Solicitud) obj;
		if (idSolicitud != other.idSolicitud)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Solicitud [idSolicitud=" + idSolicitud + ", archivo=" + archivo + ", comentarios=" + comentarios
				+ ", estado=" + estado + ", fecha=" + fecha + ", usuario=" + usuario + ", vacante=" + vacante + "]";
	}

	
	
	

}