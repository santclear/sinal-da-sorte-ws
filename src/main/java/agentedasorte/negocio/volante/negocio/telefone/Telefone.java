package agentedasorte.negocio.volante.negocio.telefone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import agentedasorte.negocio.volante.negocio.usuario.Usuario;

@Entity
@Table(name = "telefone", catalog = "bdagente")
public class Telefone implements java.io.Serializable {

	private int id;
	private Usuario usuario;
	private String telefone;

	public Telefone() {
	}

	public Telefone(int id, Usuario usuario) {
		this.id = id;
		this.usuario = usuario;
	}

	public Telefone(int id, Usuario usuario, String telefone) {
		this.id = id;
		this.usuario = usuario;
		this.telefone = telefone;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "telefone", length = 20)
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
