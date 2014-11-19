package gob.osinergmin.fise.dao;

import gob.osinergmin.base.dao.GenericDao;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13ACPK;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

public interface Formato13ACDao extends GenericDao {

	List<FiseFormato13AC> buscarFormato13AC(String codEmpresa, long anioDesde, long mesDesde, long anioHasta, long mesHasta, String etapa);
	FiseFormato13AC savecabecera(FiseFormato13AC fiseC) throws ConstraintViolationException;
	FiseFormato13AC obtenerFormato13ACByPK(FiseFormato13ACPK fiseFormato13ACPK);
	FiseFormato13AC updatecabecera(FiseFormato13AC fiseC);
	void eliminarFormato13AC(FiseFormato13AC fiseFormato13AC);
	FiseFormato13AC getCabecera(FiseFormato13ACPK fiseFormato13ACPK);
}
