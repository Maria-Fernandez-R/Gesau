package es.gob.gesau.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.gob.gesau.model.Autorizacion;

@Component
public class AutorizacionValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {		
		return Autorizacion.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Autorizacion autorizacion = (Autorizacion) target;
		
		
		if(StringUtils.isEmpty(autorizacion.getCodMeyss())) errors.rejectValue("codMeyss", "NotNull", "Campo requerido");
		if(autorizacion.getModelo()==null) errors.rejectValue("modelo", "NotNull", "Campo requerido");
		if(autorizacion.getPermiso()==null) errors.rejectValue("permiso", "NotNull", "Campo requerido");
		if(autorizacion.getViaAcceso()==null) errors.rejectValue("viaAcceso", "NotNull", "Campo requerido");
		
		//De momento no se validan porque pueden ser null en la BBDD
		/* 
		if(autorizacion.getNumPlazo()==null) errors.rejectValue("numPlazo", "NotNull", "Campo requerido");
		if(StringUtils.isEmpty(autorizacion.getTipoPlazo())) errors.rejectValue("tipoPlazo", "NotNull", "Campo requerido");
		if(StringUtils.isEmpty(autorizacion.getSilencio())) errors.rejectValue("silencio", "NotNull", "Campo requerido");
		if(StringUtils.isEmpty(autorizacion.getEpigrafeTasa052())) errors.rejectValue("epigrafeTasa052", "NotNull", "Campo requerido");
		if(StringUtils.isEmpty(autorizacion.getEpigrafeTasa062())) errors.rejectValue("codMeyss", "NotNull", "Campo requerido");
		if(StringUtils.isEmpty(autorizacion.getDosVecesSmi())) errors.rejectValue("dosVecesSmi", "NotNull", "Campo requerido");
		if(StringUtils.isEmpty(autorizacion.getAutorizaTrabajar())) errors.rejectValue("autorizaTrabajar", "NotNull", "Campo requerido");
		if(StringUtils.isEmpty(autorizacion.getDuracion())) errors.rejectValue("duracion", "NotNull", "Campo requerido");
		*/
		
	}

}
