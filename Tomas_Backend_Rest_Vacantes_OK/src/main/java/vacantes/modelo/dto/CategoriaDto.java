package vacantes.modelo.dto;

import java.util.List;

import lombok.Value;
import vacantes.modelo.entidades.Categoria;

@Value
public class CategoriaDto {
	private int idCategoria;
	private String nombre;
	
	public static List<CategoriaDto> from(List<Categoria> categoriaEntities) {
		return categoriaEntities.stream().map(CategoriaDto::from).toList();
	}
	
	public static CategoriaDto from(Categoria categoriaEntities) {
		return new CategoriaDto(
				categoriaEntities.getIdCategoria(),
				categoriaEntities.getNombre()
				);
	}
}
