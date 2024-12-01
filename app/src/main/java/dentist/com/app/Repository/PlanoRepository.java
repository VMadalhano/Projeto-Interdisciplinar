package dentist.com.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dentist.com.app.Entities.Planos;

@Repository
public interface PlanoRepository extends JpaRepository<Planos, Integer> {
    
    @Query(value = "SELECT * FROM planos WHERE id = (SELECT MAX(id) FROM planos)", nativeQuery = true)
    Planos findUltimoPlanoCadastrado();

    @Procedure(procedureName = "sp_CadastrarPlano")
        void cadastrarPlano(
        @Param("status") String status,
        @Param("descricao") String descricao,
        @Param("valormensal") int valormensal,
        @Param("valoranual") int valoranual);
}
