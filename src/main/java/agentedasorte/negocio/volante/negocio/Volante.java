package agentedasorte.negocio.volante.negocio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Volante implements Serializable {	
	private static final long serialVersionUID = 5319658223547140240L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	
	public Volante() {}

	public Volante(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "Volante{" + "id=" + id + ", nome='" + nome + '}';
	}
}
