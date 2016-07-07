package agentedasorte.negocio.volante.negocio.acesso;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "acesso", catalog = "bdagente")
public class Acesso implements java.io.Serializable {

	private Integer id;
	private String acesso;
	private int contaId;
	private Set contas = new HashSet(0);

	public Acesso() {
	}

	public Acesso(String acesso, int contaId) {
		this.acesso = acesso;
		this.contaId = contaId;
	}

	public Acesso(String acesso, int contaId, Set contas) {
		this.acesso = acesso;
		this.contaId = contaId;
		this.contas = contas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "acesso", nullable = false, length = 10)
	public String getAcesso() {
		return this.acesso;
	}

	public void setAcesso(String acesso) {
		this.acesso = acesso;
	}

	@Column(name = "conta_id", nullable = false)
	public int getContaId() {
		return this.contaId;
	}

	public void setContaId(int contaId) {
		this.contaId = contaId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "acesso")
	public Set getContas() {
		return this.contas;
	}

	public void setContas(Set contas) {
		this.contas = contas;
	}

}
