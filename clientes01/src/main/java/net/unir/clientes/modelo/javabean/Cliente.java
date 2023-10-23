package net.unir.clientes.modelo.javabean;

import java.io.Serializable;
import java.util.Date;

public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private int idCliente;
	private String nombre;
	private int cantidadEmpleados;
	private double facturacionAnual;
	private Date fechaAlta;
	private Date fechaCreacion;
	
	
	
	public Cliente(int idCliente, String nombre, int cantidadEmpleados, double facturacionAnual, Date fechaAlta,
			Date fechaCreacion) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.cantidadEmpleados = cantidadEmpleados;
		this.facturacionAnual = facturacionAnual;
		this.fechaAlta = fechaAlta;
		this.fechaCreacion = fechaCreacion;
	}

	public Cliente(int idCliente, String nombre, int cantidadEmpleados, double facturacionAnual) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.cantidadEmpleados = cantidadEmpleados;
		this.facturacionAnual = facturacionAnual;
	}
	
	public Cliente(String nombre, int cantidadEmpleados, double facturacionAnual) {
		super();
		this.nombre = nombre;
		this.cantidadEmpleados = cantidadEmpleados;
		this.facturacionAnual = facturacionAnual;
	}
	public Cliente() {
		super();
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidadEmpleados() {
		return cantidadEmpleados;
	}
	public void setCantidadEmpleados(int cantidadEmpleados) {
		this.cantidadEmpleados = cantidadEmpleados;
	}
	public double getFacturacionAnual() {
		return facturacionAnual;
	}
	public void setFacturacionAnual(double facturacionAnual) {
		this.facturacionAnual = facturacionAnual;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", cantidadEmpleados=" + cantidadEmpleados
				+ ", facturacionAnual=" + facturacionAnual + ", fechaAlta=" + fechaAlta + ", fechaCreacion="
				+ fechaCreacion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCliente;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		if (idCliente != other.idCliente)
			return false;
		return true;
	}
	
	//Metodos propios
	
	public double ratioFacturacion() {
		return facturacionAnual / cantidadEmpleados;
	}
	
	
	
	

}
