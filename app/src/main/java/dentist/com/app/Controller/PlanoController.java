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
@RequestMapping("/plano")
public class PlanoController {

    @Autowired
    private final PlanoService planoService;

    @GetMapping
    public ResponseEntity<List<Planos>> listarPlanos() {
        List<Planos> plano = planoService.listarPlanos();
        return ResponseEntity.ok().body(plano);
    }

// Buscar plano por ID
    @GetMapping("/{id}")
    public ResponseEntity<Planos> buscarPlano(@PathVariable Integer id) {
        Optional<Planos> plano = planoService.buscarPlanoPorId(id);

        return plano.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

// Criar um novo plano
    @PostMapping
    public ResponseEntity<Planos> criarPlano(@RequestBody Planos plano) {
        Planos novoPlano = planoService.criar(plano);
        if(novoPlano != null){
            return new ResponseEntity<>(novoPlano, HttpStatus.CREATED);
        }else{
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
