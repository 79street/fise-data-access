package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato14CD;
import gob.osinergmin.fise.domain.FiseFormato14CDOb;
import gob.osinergmin.fise.domain.FiseFormato14CDObPK;

import java.sql.SQLException;
import java.util.List;

public interface Formato14CDObDao {
	
    List<FiseFormato14CDOb> listarFiseFormato14CDOb();	
	
	void insertarFiseFormato14CDOb(FiseFormato14CDOb fiseFormato14CDOb) 
			throws SQLException;

	void actualizarFiseFormato14CDOb(FiseFormato14CDOb fiseFormato14CDOb) 
			throws SQLException;

	void eliminarFiseFormato14CDOb(FiseFormato14CDOb fiseFormato14CDOb) 
			throws SQLException;
	
	FiseFormato14CD obtenerFiseFormato14CDOb(FiseFormato14CDObPK id) 
			throws SQLException;

}