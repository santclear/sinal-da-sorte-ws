package agentedasorte.negocio;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Abstract base class for entities. Allows parameterization of id type, chooses auto-generation and implements {@link #equals(Object)} and {@link #hashCode()} based on that id.
 * 
 * @author Oliver Gierke
 * @author Thomas Darimont
 * @author Santclear Da Costa
 * @param <PK>
 *            the type of the identifier.
 */
@MappedSuperclass
public abstract class SimpleAbstractPersistableAgs {


	@Id
	@GeneratedValue
	//@JsonSerialize(using = IntToStringSerializer.class, as=String.class)
	@JsonProperty("_id")
	private int id;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Persistable#getId()
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the entity.
	 * 
	 * @param id
	 *            the id to set
	 */
	protected void setId(final int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
	}
	
}