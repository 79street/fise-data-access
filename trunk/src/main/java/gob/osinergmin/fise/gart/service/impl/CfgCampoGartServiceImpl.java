package gob.osinergmin.fise.gart.service.impl;

import gob.osinergmin.fise.domain.CfgCampo;
import gob.osinergmin.fise.domain.CfgTabla;
import gob.osinergmin.fise.gart.dao.CfgCampoGartDao;
import gob.osinergmin.fise.gart.service.CfgCampoGartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value="cfgCampoGartServiceImpl")
public class CfgCampoGartServiceImpl implements CfgCampoGartService {

	@Autowired
	@Qualifier("cfgCampoGartDaoImpl")
	private CfgCampoGartDao cfgCampoGartDao;
	
	@Override
	public List<CfgCampo> listarCamposByTabla(CfgTabla cfgTabla) {
		return cfgCampoGartDao.listarCamposByTabla(cfgTabla);
	}
	
	@Override
	public int obtenerPosicionFinalCampo(List<CfgCampo> listaCampo, String nombreCampo ){
		int posicionFinal=0;
		int nroCampo = 0;
		for (CfgCampo campo : listaCampo) {
			//siempre revisar que los archivos esten ordenados
			nroCampo++;
			if(nroCampo<=listaCampo.size()){
				posicionFinal = posicionFinal + campo.getLongitud().intValue();
				if(nombreCampo.equals(campo.getCodCampo().trim())){
					break;
				}
			}else{
				posicionFinal=0;
				break;
			}
		}
		return posicionFinal;
	}
	
	/*@Override
	public int obtenerPosicionFinalCampo(List<CfgCampo> listaCampo, String nombreCampo){
		int posicionFinal = 0;
		posicionFinal = posicionFinal + obtenerPosicionInicialCampo(listaCampo, nombreCampo);
		return posicionFinal;
	}*/
	
	@Override
	public int longitudMaximaRegistro(List<CfgCampo> listaCampo){
		int longitud=0;
		for (CfgCampo campo : listaCampo) {
			longitud = longitud + campo.getLongitud().intValue();
		}
		return longitud;
	}
	
}
