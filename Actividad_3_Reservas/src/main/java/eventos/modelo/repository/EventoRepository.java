package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import eventos.modelo.entitis.Evento;

/**
 * Repositorio para la entidad Evento que proporciona métodos personalizados de consulta.
 */
@Repository
public interface EventoRepository extends JpaRepository <Evento, Integer>{

	/**
     * Encuentra eventos por estado.
     *
     * @param estado el estado del evento
     * @return una lista de eventos que coinciden con el estado especificado y tienen fecha de inicio posterior a la fecha actual
     */
	@Query("select e from Evento e where e.estado=?1 AND e.fechaInicio>current_date")
	public List <Evento> findEventoPorEstado(String estado);
	
	 /**
     * Encuentra eventos destacados.
     *
     * @return una lista de eventos destacados que tienen fecha de inicio posterior a la fecha actual
     */
	@Query("select e from Evento e where e.destacado='S' AND e.fechaInicio>current_date")
	public List <Evento> findEventoDestacado();
	
	 /**
     * Encuentra eventos por estado y destacados.
     *
     * @return una lista de eventos que están activos, destacados y tienen fecha de inicio posterior a la fecha actual
     */
	@Query("select e from Evento e where e.destacado='S' AND e.estado='ACTIVO' AND  e.fechaInicio>current_date")
	public List <Evento> findEventoPorEstadoYDestacado();
	
	 /**
     * Encuentra eventos por tipo.
     *
     * @param idTipo el ID del tipo de evento
     * @return una lista de eventos que pertenecen al tipo especificado y tienen fecha de inicio posterior a la fecha actual
     */
	@Query("select e from Evento e where e.tipo.idTipo=?1 AND e.fechaInicio>current_date")
	public List <Evento> findEventoPorTipo(int idTipo);

}
