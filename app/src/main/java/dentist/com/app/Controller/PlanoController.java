package dentist.com.app.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dentist.com.app.Entities.Planos;
import dentist.com.app.Services.PlanoService;

@RestController
@RequestMapping("/api/plano")
public class PlanoController {

    @Autowired
    private PlanoService planoService;

    @GetMapping
    public List<Planos> listarPlanos() {

        return planoService.listarPlanos();
    }

// Buscar plano por ID
    @GetMapping("/{id}")
    public ResponseEntity<Planos> buscarPlano(@PathVariable Integer id) {
        Optional<Planos> plano = planoService.buscarPlanoPorId(id);

        return plano.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

// Criar um novo plano
    @PostMapping
    public ResponseEntity<Planos> criarPlano(@RequestBody Planos plano) {
        Planos novoPlano = planoService.criarPlano(plano);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoPlano);
    }

// Atualizar plano existente
    @PutMapping("/{id}")
    public ResponseEntity<Planos> atualizarPlano(@PathVariable Integer id, @RequestBody Planos plano) {
        Optional<Planos> planoExistente = planoService.buscarPlanoPorId(id);
        if (planoExistente.isPresent()) {
            Planos planoAtualizado = planoService.atualizarPlano(id, plano);
            return ResponseEntity.ok(planoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
// Deletar plano

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable Integer id) {
        Optional<Planos> planoExistente = planoService.buscarPlanoPorId(id);
        if (planoExistente.isPresent()) {
            planoService.deletarPlano(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
