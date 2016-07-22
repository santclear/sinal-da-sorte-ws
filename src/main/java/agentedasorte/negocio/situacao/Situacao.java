package agentedasorte.negocio.situacao;

import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import agentedasorte.negocio.conta.Conta;

@Entity
@Table(name = "situacao", catalog = "bdagente")
public class Situacao extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -8248956091286313313L;

	@Column(name = "descricao", nullable = false, length = 10)
	private String descricao;

	@JsonIgnore
	@OneToMany(mappedBy = "situacao")
	private List<Conta> contas;

	public Situacao() {
	}

	public Situacao(String descricao) {
		this.descricao = descricao;
	}

	public Situacao(String descricao, List<Conta> contas) {
		this.descricao = descricao;
		this.contas = contas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((contas == null) ? 0 : contas.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Situacao))
			return false;
		Situacao other = (Situacao) obj;
		if (contas == null) {
			if (other.contas != null)
				return false;
		} else if (!contas.equals(other.contas))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<Conta> getContas() {
		return this.contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
}
