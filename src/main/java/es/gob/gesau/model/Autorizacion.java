package es.gob.gesau.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="lga_autorizaciones")
public class Autorizacion {
	
	@Id
	private String codMeyss;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_permiso")
	private Permiso permiso;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_via")
	private ViaAcceso viaAcceso;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_modelo")
	private Modelo modelo;

	@Column(length = 11)
	private Integer numPlazo;

	@Column(length = 1)
	private String tipoPlazo;

	@Column(length = 1)
	private String silencio;

	@Column(name="epigrafe_tasa_052", length = 9)
	private String epigrafeTasa052;

	@Column(name="epigrafe_tasa_062", length = 9)
	private String epigrafeTasa062;

	@Column(name="dos_veces_smi", length = 1)
	private String dosVecesSmi;

	@Column(length = 1)
	private String autorizaTrabajar;

	@Column(length = 3)
	private String duracion;
	
	@Transient
	private String plazoCompleto;
	

	public String getCodMeyss() {
		return codMeyss;
	}

	public void setCodMeyss(String codMeyss) {
		this.codMeyss = codMeyss;
	}

	public Permiso getPermiso() {
		return permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}

	public ViaAcceso getViaAcceso() {
		return viaAcceso;
	}

	public void setViaAcceso(ViaAcceso viaAcceso) {
		this.viaAcceso = viaAcceso;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Integer getNumPlazo() {
		return numPlazo;
	}

	public void setNumPlazo(Integer numPlazo) {
		this.numPlazo = numPlazo;
	}

	public String getTipoPlazo() {
		return tipoPlazo;
	}

	public void setTipoPlazo(String tipoPlazo) {
		this.tipoPlazo = tipoPlazo;
	}

	public String getSilencio() {		
		return (silencio.equals("P"))?"Positivo":"Negativo";
	}

	public void setSilencio(String silencio) {
		
		if(silencio!=null) {
			silencio = silencio.trim();
			if(silencio.equalsIgnoreCase("P")) {
				this.silencio = "P";
			} else {
				this.silencio = "N";
			}
		} else {
			this.silencio = "N";
		}
	}

	public String getEpigrafeTasa052() {
		return epigrafeTasa052;
	}

	public void setEpigrafeTasa052(String epigrafeTasa052) {
		this.epigrafeTasa052 = epigrafeTasa052;
	}

	public String getEpigrafeTasa062() {
		return epigrafeTasa062;
	}

	public void setEpigrafeTasa062(String epigrafeTasa062) {
		this.epigrafeTasa062 = epigrafeTasa062;
	}

	public String getDosVecesSmi() {
		return dosVecesSmi;
	}

	public void setDosVecesSmi(String dosVecesSmi) {
		
		String res = "N";
		
		//TODO: caso "3.2.1 b)" es si o no??
		
		if(dosVecesSmi!=null && StringUtils.isNotBlank(dosVecesSmi)) {				
			if(StringUtils.containsIgnoreCase(dosVecesSmi, "no")) res = "N";
			if(StringUtils.containsIgnoreCase(dosVecesSmi, "si")) res = "S";
			if(StringUtils.containsIgnoreCase(dosVecesSmi, "sí")) res = "S";				
			if(StringUtils.containsIgnoreCase(dosVecesSmi, "n")) res = "N";				
			if(StringUtils.containsIgnoreCase(dosVecesSmi, "s")) res = "S";	
		}
		
		this.dosVecesSmi = res;
	}

	public String getAutorizaTrabajar() {
		return autorizaTrabajar;
	}

	public void setAutorizaTrabajar(String autorizaTrabajar) {
		String res = "N";
		
		if(autorizaTrabajar!=null) {				
			if(StringUtils.containsIgnoreCase(autorizaTrabajar, "no")) res = "N";
			if(StringUtils.containsIgnoreCase(autorizaTrabajar, "si")) res = "S";
			if(StringUtils.containsIgnoreCase(autorizaTrabajar, "sí")) res = "S";				
			if(StringUtils.containsIgnoreCase(autorizaTrabajar, "C/A")) res = "S";				
			if(StringUtils.containsIgnoreCase(autorizaTrabajar, "C/P")) res = "S";	
		}
		
		this.autorizaTrabajar = res;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		String res = "DE";
		
		//TODO: caso "Igual que el familiar que depende"
		//TODO: caso "360 dias"
		
		if(duracion!=null) {				
			Pattern pattern = Pattern.compile("\\d+");
			Matcher matcher = pattern.matcher(duracion);
			if(matcher.find()) {
				if(StringUtils.containsIgnoreCase(duracion, "dia")) res = matcher.group();
				if(StringUtils.containsIgnoreCase(duracion, "día")) res = matcher.group();
				if(StringUtils.containsIgnoreCase(duracion, "mes")) res = matcher.group();
				if(StringUtils.containsIgnoreCase(duracion, "año")) res = (Integer.valueOf(matcher.group())*12)+"";				
			}
			
			if(StringUtils.containsIgnoreCase(duracion, "no definida")) res = "DE";	//desconocido			
			if(StringUtils.containsIgnoreCase(duracion, "menos")) res = "CO";//condicionada				
			if(StringUtils.containsIgnoreCase(duracion, "max")) res = "CO";//condicionada				
			if(StringUtils.containsIgnoreCase(duracion, "resto")) res = "CO";//condicionada				
			if(StringUtils.containsIgnoreCase(duracion, "estudio")) res = "CO";//condicionada				
			if(StringUtils.containsIgnoreCase(duracion, "hasta")) res = "CO";//condicionada				
			if(StringUtils.containsIgnoreCase(duracion, "plurianual")) res = "IN";//indefinida
		}
		
		this.duracion = res;
	}
	

	public String getPlazoCompleto() {
		String plazoCompleto = "";
		
		if(this.numPlazo!=null && this.tipoPlazo!=null) {
			plazoCompleto += this.numPlazo.toString();
			if (this.numPlazo==1) {	
				if(this.tipoPlazo.equals("D")) plazoCompleto += " día";
				if(this.tipoPlazo.equals("M")) plazoCompleto += " mes";
				if(this.tipoPlazo.equals("A")) plazoCompleto += " año";
			} else {
				if(this.tipoPlazo.equals("D")) plazoCompleto += " días";
				if(this.tipoPlazo.equals("M")) plazoCompleto += " meses";
				if(this.tipoPlazo.equals("A")) plazoCompleto += " años";
			}
		}
		
		return plazoCompleto;
	}

	public void setPlazoCompleto(String plazoCompleto) {
		
		//TODO: modificar para incluir el caso que plazo completo sea "3M"
		if(plazoCompleto!=null && StringUtils.isNotBlank(plazoCompleto)) {
			
			plazoCompleto = plazoCompleto.trim();
			
			if(plazoCompleto.split(" ").length>=2) {
				
				String numero = plazoCompleto.split(" ")[0];
				String tipoPlazo = plazoCompleto.split(" ")[1];
				
				if(numero!=null) {
					this.numPlazo=Integer.valueOf(numero);
				}
				
				if (tipoPlazo!=null) {	
					if(tipoPlazo.equalsIgnoreCase("día")) this.tipoPlazo = "D";
					if(tipoPlazo.equalsIgnoreCase("dia")) this.tipoPlazo = "D";
					if(tipoPlazo.equalsIgnoreCase("mes")) this.tipoPlazo = "M";
					if(tipoPlazo.equalsIgnoreCase("año")) this.tipoPlazo = "A";
					if(tipoPlazo.equalsIgnoreCase("días")) this.tipoPlazo = "D";
					if(tipoPlazo.equalsIgnoreCase("dias")) this.tipoPlazo = "D";
					if(tipoPlazo.equalsIgnoreCase("meses")) this.tipoPlazo = "M";
					if(tipoPlazo.equalsIgnoreCase("años")) this.tipoPlazo = "A";
				}
			}
		}	
		
		this.plazoCompleto = plazoCompleto;
	}
	
	

	@Override
	public String toString() {
		return "Autorizacion [codMeyss=" + codMeyss + ", permiso=" + permiso + ", viaAcceso=" + viaAcceso + ", modelo="
				+ modelo + ", numPlazo=" + numPlazo + ", tipoPlazo=" + tipoPlazo + ", silencio=" + silencio
				+ ", epigrafeTasa052=" + epigrafeTasa052 + ", epigrafeTasa062=" + epigrafeTasa062 + ", dosVecesSmi="
				+ dosVecesSmi + ", autorizaTrabajar=" + autorizaTrabajar + ", duracion=" + duracion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(autorizaTrabajar, codMeyss, dosVecesSmi, duracion, epigrafeTasa052, epigrafeTasa062, modelo,
				numPlazo, permiso, silencio, tipoPlazo, viaAcceso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autorizacion other = (Autorizacion) obj;
		return Objects.equals(autorizaTrabajar, other.autorizaTrabajar) && Objects.equals(codMeyss, other.codMeyss)
				&& Objects.equals(dosVecesSmi, other.dosVecesSmi) && Objects.equals(duracion, other.duracion)
				&& Objects.equals(epigrafeTasa052, other.epigrafeTasa052)
				&& Objects.equals(epigrafeTasa062, other.epigrafeTasa062) && Objects.equals(modelo, other.modelo)
				&& Objects.equals(numPlazo, other.numPlazo) && Objects.equals(permiso, other.permiso)
				&& Objects.equals(silencio, other.silencio) && Objects.equals(tipoPlazo, other.tipoPlazo)
				&& Objects.equals(viaAcceso, other.viaAcceso);
	}

	
	
	

	
	

}
