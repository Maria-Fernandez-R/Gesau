package es.gob.gesau.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="lga_modelos")
public class Modelo {
	
	@Id
	private String id;
	
	@Column(length = 300)
	private String desModelo;
	
	@OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Autorizacion> autorizaciones;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesModelo() {
		return desModelo;
	}

	public void setDesModelo(String desModelo) {
		this.desModelo = desModelo;
	}

	public List<Autorizacion> getAutorizaciones() {
		return autorizaciones;
	}

	public void setAutorizaciones(List<Autorizacion> autorizaciones) {
		this.autorizaciones = autorizaciones;
	}

	
	@Override
	public String toString() {
		return "Modelo [id=" + id + ", desModelo=" + desModelo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(desModelo, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modelo other = (Modelo) obj;
		return Objects.equals(desModelo, other.desModelo) && Objects.equals(id, other.id);
	}
	
	
	

}
