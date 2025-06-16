package controleTempPersist.model.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="Temperatura")
public class Temperatura {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hora")
    private int hora;
    
    @Column(name = "valor")
    private int valor;

    public Temperatura() {}

    public Temperatura(int hora, int valor) {
        this.hora = hora;
        this.valor = valor;
    }

	public Long getId() {
		return id;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
    
    
}
