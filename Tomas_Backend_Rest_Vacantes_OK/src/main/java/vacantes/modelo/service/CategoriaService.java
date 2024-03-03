package vacantes.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vacantes.modelo.entidades.Categoria;
import vacantes.modelo.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria encontrarCategoria (int idCategoria) {
		return categoriaRepository.findById(idCategoria).get();
	}
	
	public List<Categoria>mostrarTodasCategorias() {
		return categoriaRepository.findAll();
	}
}
