package dentist.com.app.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dentist.com.app.Entities.Planos;
import dentist.com.app.Repository.PlanoRepository;

@Service
public class PlanoService {
    @Autowired
    private PlanoRepository planoRepository;
    //@Autowired
    //private EntityManager entityManager;

    public List<Planos> findAll() {
        return planoRepository.findAll();
    }

    public Optional<Planos> buscarPlanoPorId(int id) {
        return planoRepository.findById(id);
    }

    @Transactional
        public Planos criar(Planos plano) {
            try {
                // Chama o procedimento armazenado para cadastrar o plano
                planoRepository.cadastrarPlano(plano.getStatus(), plano.getDescricao(), plano.getValormensal(), plano.getValoranual());
                // Retorna o último plano cadastrado
                return planoRepository.findUltimoPlanoCadastrado();
            } catch (Exception e) {
                System.err.println("Erro ao cadastrar plano: " + e.getMessage());
                return null;  // Caso haja erro, retorna null
            }
        }

    public Planos atualizarPlano(Integer id, Planos plano) {
        plano.setId(id);
        return planoRepository.save(plano);
    }

    public void deletarPlano(Integer id) {
        planoRepository.deleteById(id);
    }

}
