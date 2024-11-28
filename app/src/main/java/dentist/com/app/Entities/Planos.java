package dentist.com.app.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "planos")
public class Planos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    private int id;
    @Column(name = "status", length = 50, nullable = true)
    private String status;
    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;
    @Column(name = "valormensal", length = 50, nullable = false)
    private int valormensal;
    @Column(name = "valoranual", length = 50, nullable = false)
    private int valoranual;
}
