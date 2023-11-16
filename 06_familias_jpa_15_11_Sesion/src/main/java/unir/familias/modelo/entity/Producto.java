package unir.familias.modelo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_producto")
	private int idProducto;
	private String descripcion;
	private String marca;
	@Column(name="precio_unitario")
	private double precioUnitario;
	private int stock;
	@Column(name="fecha_alta")
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	@ManyToOne
	@JoinColumn(name="id_familia")
	private Familia familia;

}
