package cajero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cajero.modelo.entitybean.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{

}
