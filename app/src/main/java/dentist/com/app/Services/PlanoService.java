package dentist.com.app.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dentist.com.app.Entities.Planos;
import dentist.com.app.Repository.PlanoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class PlanoService {

    private PlanoRepository planoRepository;
    private EntityManager entityManager;

    public List<Planos> listarPlanos() {
        return planoRepository.findAll();
    }

    public Optional<Planos> buscarPlanoPorId(Integer id) {
        return planoRepository.findById(id);
    }

    public PlanoService(PlanoRepository planoRepository, EntityManager entityManager) {
        this.planoRepository = planoRepository;
        this.entityManager = entityManager;
    }


    @Transactional
    public void cadastrarPlano(Planos plano) {
        StoredProcedureQuery query = entityManager
            .createStoredProcedureQuery("sp_CadastrarPlano")
            .registerStoredProcedureParameter("status", String.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter("descricao", String.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter("valormensal", Float.class, jakarta.persistence.ParameterMode.IN)
            .registerStoredProcedureParameter("valoranual", Float.class, jakarta.persistence.ParameterMode.IN)
            .setParameter("status", plano.getStatus())
            .setParameter("descricao", plano.getDescricao())
            .setParameter("valormensal", plano.getValormensal())
            .setParameter("valoranual", plano.getValoranual());

        query.execute();
    }

    /*public Planos criarPlano(Planos plano) {
        return planoRepository.save(plano);
    }*/

    public Planos atualizarPlano(Integer id, Planos plano) {
        plano.setId(id);
        return planoRepository.save(plano);
    }

    public void deletarPlano(Integer id) {
        planoRepository.deleteById(id);
    }

}
