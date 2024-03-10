package vacantes.modelo.dto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import vacantes.modelo.entidades.EstatusVacante;
import vacantes.modelo.entidades.Vacante;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the vacantes database table.
 * 
 */
@Data
@AllArgsConstructor
public class VacanteDto{
	private int idVacante;
	private String descripcion;
	private int destacado;
	private String detalles;
	@Enumerated(value = EnumType.STRING)
	private EstatusVacante estatus;
	private Date fecha;
	private String imagen;
	private String nombre;
	private double salario;
	private int idCategoria;
	private Boolean existeSolicitud;
	
	public static List<VacanteDto> from(List<Vacante> vacantesEntities) {
		return vacantesEntities.stream().map(VacanteDto::from).toList();
	}
	
	public static VacanteDto from(Vacante vacanteEntity) {
		return new VacanteDto(
				vacanteEntity.getIdVacante(),
				vacanteEntity.getDescripcion(),
				vacanteEntity.getDestacado(), 
				vacanteEntity.getDetalles(), 
				vacanteEntity.getEstatus(),
				vacanteEntity.getFecha(), 
				vacanteEntity.getImagen(), 
				vacanteEntity.getNombre(),
				vacanteEntity.getSalario(), 
				vacanteEntity.getCategoria().getIdCategoria(), 
				null);
	}
	
	
}