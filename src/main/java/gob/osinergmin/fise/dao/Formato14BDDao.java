package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato14BC;
import gob.osinergmin.fise.domain.FiseFormato14BD;
import gob.osinergmin.fise.domain.FiseFormato14BDPK;

import java.util.List;

public interface Formato14BDDao extends GenericDao {
	
	FiseFormato14BD obtenerFormato14BDByPK(FiseFormato14BDPK fiseFormato14BDPK);
	List<FiseFormato14BD> listarFormato14BDByFormato14BC(FiseFormato14BC formato14BC);
	FiseFormato14BD obtenerFormato14BDVigente(String codEmpresa, long anioVigencia, long idZonaBenef);
	void registrarFormato14BD(FiseFormato14BD fiseFormato14BD);
	void modificarFormato14BD(FiseFormato14BD fiseFormato14BD);
	void eliminarFormato14BD(FiseFormato14BD fiseFormato14BD);

}
