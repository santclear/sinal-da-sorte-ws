package agentedasorte.negocio.volante.negocio.situacao;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "situacao", catalog = "bdagente")
public class Situacao implements java.io.Serializable {

	private int id;
	private String situacao;
	private int contaId;
	private Set contas = new HashSet(0);

	public Situacao() {
	}

	public Situacao(int id, String situacao, int contaId) {
		this.id = id;
		this.situacao = situacao;
		this.contaId = contaId;
	}

	public Situacao(int id, String situacao, int contaId, Set contas) {
		this.id = id;
		this.situacao = situacao;
		this.contaId = contaId;
		this.contas = contas;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "situacao", nullable = false, length = 10)
	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Column(name = "conta_id", nullable = false)
	public int getContaId() {
		return this.contaId;
	}

	public void setContaId(int contaId) {
		this.contaId = contaId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "situacao")
	public Set getContas() {
		return this.contas;
	}

	public void setContas(Set contas) {
		this.contas = contas;
	}

}
