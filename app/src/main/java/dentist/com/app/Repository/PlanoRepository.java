package dentist.com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dentist.com.app.Entities.Planos;

@Repository
public interface PlanoRepository extends JpaRepository<Planos, Integer> {

}
