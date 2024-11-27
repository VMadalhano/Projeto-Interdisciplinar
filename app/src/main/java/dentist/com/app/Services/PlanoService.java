package dentist.com.app.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dentist.com.app.Entities.Planos;
import dentist.com.app.Repository.PlanoRepository;

@Service
public class PlanoService {
    private PlanoRepository planoRepository;

    public List<Planos> listarPlanos() {
        return planoRepository.findAll();
    }

    public Optional<Planos> buscarPlanoPorId(Integer id) {
        return planoRepository.findById(id);
    }

    public Planos criarPlano(Planos plano) {
        return planoRepository.save(plano);
    }

    public Planos atualizarPlano(Integer id, Planos plano) {
        plano.setId(id); 
        return planoRepository.save(plano);
    }

    public void deletarPlano(Integer id) {
        planoRepository.deleteById(id);
    }

}
