package es.gob.gesau.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="lga_permisos")
public class Permiso {
	
	@Id
	private String id;
	
	@Column(nullable = false)
	private String desPermiso;
	
	@Column(nullable = false)
	private String lucrativo;
	
	@Column
	private String residencia;
	
	@Column
	private String viaDefecto;
	
	@Column
	private Integer mesesValidez;
	
	@Column
	private String reglamento;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesPermiso() {
		return desPermiso;
	}

	public void setDesPermiso(String des_permiso) {
		this.desPermiso = des_permiso;
	}

	public String getLucrativo() {
		return lucrativo;
	}

	public void setLucrativo(String lucrativo) {
		this.lucrativo = lucrativo;
	}

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}

	public String getViaDefecto() {
		return viaDefecto;
	}

	public void setViaDefecto(String viaDefecto) {
		this.viaDefecto = viaDefecto;
	}

	public Integer getMesesValidez() {
		return mesesValidez;
	}

	public void setMesesValidez(Integer mesesValidez) {
		this.mesesValidez = mesesValidez;
	}

	public String getReglamento() {
		return reglamento;
	}

	public void setReglamento(String reglamento) {
		this.reglamento = reglamento;
	}

	@Override
	public String toString() {
		return "Permisos [id=" + id + ", des_permiso=" + desPermiso + ", lucrativo=" + lucrativo + ", residencia="
				+ residencia + ", viaDefecto=" + viaDefecto + ", mesesValidez=" + mesesValidez + ", reglamento="
				+ reglamento + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(desPermiso, id, lucrativo, mesesValidez, reglamento, residencia, viaDefecto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permiso other = (Permiso) obj;
		return Objects.equals(desPermiso, other.desPermiso) && Objects.equals(id, other.id)
				&& Objects.equals(lucrativo, other.lucrativo) && Objects.equals(mesesValidez, other.mesesValidez)
				&& Objects.equals(reglamento, other.reglamento) && Objects.equals(residencia, other.residencia)
				&& Objects.equals(viaDefecto, other.viaDefecto);
	}
	
	
}
