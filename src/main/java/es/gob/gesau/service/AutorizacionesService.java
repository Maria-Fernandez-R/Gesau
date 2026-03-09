package es.gob.gesau.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.gob.gesau.model.Autorizacion;
import es.gob.gesau.repository.AutorizacionRepository;
import es.gob.gesau.repository.ModeloRepository;
import es.gob.gesau.repository.PermisoRepository;
import es.gob.gesau.repository.ViaAccesoRepository;
import es.gob.gesau.validator.AutorizacionValidator;

@Service
public class AutorizacionesService {

	private final AutorizacionRepository autorizacionRepository;
	private final ModeloRepository modeloRepository;
	private final PermisoRepository permisoRepository;
	private final ViaAccesoRepository viaAccesoRepository;
	private final AutorizacionValidator autorizacionValidator;
	
	
	private final int MODELO = 0;
	private final int PERMISO = 10;
	private final int VIA_ACCESO = 11;
	private final int PLAZO = 7;
	private final int SILENCIO = 8;
	private final int COD_MEYSS = 9;
	private final int EPIGRAFE_TASA_052 = 18;
	private final int EPIGRAFE_TASA_062 = 19;
	private final int DOS_VECES_SMI= 20;
	private final int AUTORIZA_TRABAJAR = 21;
	private final int DURACION = 22;
	





	public AutorizacionesService(AutorizacionRepository autorizacionRepository, ModeloRepository modeloRepository,
			PermisoRepository permisoRepository, ViaAccesoRepository viaAccesoRepository,
			AutorizacionValidator autorizacionValidator) {
		super();
		this.autorizacionRepository = autorizacionRepository;
		this.modeloRepository = modeloRepository;
		this.permisoRepository = permisoRepository;
		this.viaAccesoRepository = viaAccesoRepository;
		this.autorizacionValidator = autorizacionValidator;
	}



	public List<Autorizacion> getAllAutorizaciones() {
		return autorizacionRepository.findAll();
	}

	
	
	public List<Autorizacion> cargaFicheroCodificacion(MultipartFile[] ficheroCodificacion) {

		List<Autorizacion> errores = new ArrayList<Autorizacion>();
		
		try {
			for (MultipartFile multipartFile : ficheroCodificacion) {
				
				InputStream io = multipartFile.getInputStream();
				Workbook wb = WorkbookFactory.create(io);
				Sheet sheet = null;
				
				for(int i=0; i<wb.getNumberOfSheets(); i++) {
					sheet = (Sheet) wb.getSheetAt(i);										
					int iRow = 1;// para que comienze en la fila 2 porque la fila 1 tiene los titulos
					
					
					Row row = sheet.getRow(iRow);
					while(row!=null) {
						Autorizacion autorizacion = new Autorizacion();
						
						try {							
							String modelo = (row.getCell(MODELO)!=null)?row.getCell(MODELO).toString():"";
							String permiso = (row.getCell(PERMISO)!=null)?row.getCell(PERMISO).toString():"";
							String viaAcceso = (row.getCell(VIA_ACCESO)!=null)?row.getCell(VIA_ACCESO).toString():"";
							String plazo = (row.getCell(PLAZO)!=null)?row.getCell(PLAZO).toString():"";
							String silencio = (row.getCell(SILENCIO)!=null)?row.getCell(SILENCIO).toString():"";
							String codMeyss = (row.getCell(COD_MEYSS)!=null)?row.getCell(COD_MEYSS).toString():"";
							String epigrafeTasa052 = (row.getCell(EPIGRAFE_TASA_052)!=null)?row.getCell(EPIGRAFE_TASA_052).toString():"";
							String epigrafeTasa062 = (row.getCell(EPIGRAFE_TASA_062)!=null)?row.getCell(EPIGRAFE_TASA_062).toString():"";
							String dosVecesSmi = (row.getCell(DOS_VECES_SMI)!=null)?row.getCell(DOS_VECES_SMI).toString():"";
							String autorizaTrabajar = (row.getCell(AUTORIZA_TRABAJAR)!=null)?row.getCell(AUTORIZA_TRABAJAR).toString():"";
							String duracion = (row.getCell(DURACION)!=null)?row.getCell(DURACION).toString():"";
							
							if(!StringUtils.isEmpty(modelo)) {
								if(StringUtils.contains(modelo, "-")) {
									modelo = modelo.split("-")[0].toString() + modelo.split("-")[1].toString();
								}
							}
							
							autorizacion.setModelo(modeloRepository.findById(modelo).orElse(null));
							autorizacion.setPermiso(permisoRepository.findById(permiso).orElse(null));
							autorizacion.setViaAcceso(viaAccesoRepository.findById(viaAcceso).orElse(null));
							autorizacion.setPlazoCompleto(plazo);
							autorizacion.setSilencio(silencio);
							autorizacion.setCodMeyss(codMeyss);
							autorizacion.setEpigrafeTasa052(epigrafeTasa052);
							autorizacion.setEpigrafeTasa062(epigrafeTasa062);
							autorizacion.setDosVecesSmi(dosVecesSmi);
							autorizacion.setAutorizaTrabajar(autorizaTrabajar);
							autorizacion.setDuracion(duracion);			
							
							
							if(autorizacionValidator.validateObject(autorizacion).hasErrors()) {							
								errores.add(autorizacion);							
							} else {
								autorizacionRepository.save(autorizacion);
								System.out.println("Autorizacion guardada correctamente: "+autorizacion.toString());
							}		
							
						} catch(IllegalArgumentException e) {
							e.printStackTrace();
							errores.add(autorizacion);
						} catch(Exception e) {
							e.printStackTrace();
							errores.add(autorizacion);
						}						
						iRow++;
						row = sheet.getRow(iRow);
					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(Autorizacion aut : errores) {
			System.out.println("Error: "+aut.toString());			
		}

		return errores;
	}



	public void borrarAutorizaciones() {
		
		autorizacionRepository.deleteAll();
		
	}

}
