package es.gob.gesau.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="lga_via_acceso")
public class ViaAcceso {
	
	@Id
	private String id;
	
	@Column
	private String desViaAcceso;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesViaAcceso() {
		return desViaAcceso;
	}

	public void setDesViaAcceso(String desViaAcceso) {
		this.desViaAcceso = desViaAcceso;
	}

	@Override
	public String toString() {
		return "ViaAcceso [id=" + id + ", desViaAcceso=" + desViaAcceso + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(desViaAcceso, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViaAcceso other = (ViaAcceso) obj;
		return Objects.equals(desViaAcceso, other.desViaAcceso) && Objects.equals(id, other.id);
	}
	
	

}
