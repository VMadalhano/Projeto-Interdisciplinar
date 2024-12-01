package dentist.com.app.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private int id;
    @Column(name = "status", length = 11, nullable = true)
    private String status;
    @Column(name = "descricao", length = 150, nullable = false)
    private String descricao;
    @Column(name = "valormensal", nullable = false)
    private int   valormensal;
    @Column(name = "valoranual", nullable = false)
    private int  valoranual;

    public Planos (){}

    public Planos(int id, String status, String descricao, int valormensal, int valoranual) {
        this.id = id;
        this.status = status;
        this.descricao = descricao;
        this.valormensal = valormensal;
        this.valoranual = valoranual;

    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getValormensal() {
        return valormensal;
    }

    public void setValormensal(int valormensal) {
        this.valormensal = valormensal;
    }

    public int getValoranual() {
        return valoranual;
    }

    public void setValoranual(int valoranual) {
        this.valoranual = valoranual;
    }
}
