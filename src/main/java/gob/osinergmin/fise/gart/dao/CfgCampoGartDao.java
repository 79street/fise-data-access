package gob.osinergmin.fise.gart.dao;

import gob.osinergmin.fise.domain.CfgCampo;
import gob.osinergmin.fise.domain.CfgTabla;

import java.util.List;

public interface CfgCampoGartDao {
	
	List<CfgCampo> listarCamposByTabla(CfgTabla cfgTabla);

}
