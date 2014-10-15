package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.CfgCampo;
import gob.osinergmin.fise.domain.CfgTabla;

import java.util.List;

public interface CfgCampoDao {
	
	List<CfgCampo> listarCamposByTabla(CfgTabla cfgTabla);

}
