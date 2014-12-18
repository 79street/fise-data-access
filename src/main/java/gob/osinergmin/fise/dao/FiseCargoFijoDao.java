package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseMcargofijo;
import gob.osinergmin.fise.domain.FiseMcargofijoPK;

import java.sql.SQLException;
import java.util.List;

public interface FiseCargoFijoDao {
	
	void insertarFiseCargoFijo(FiseMcargofijo fiseCargoFijo) 
			throws SQLException;
	
	void actualizarFiseObservacion(FiseMcargofijo fiseCargoFijo) 
			throws SQLException;
	
	FiseMcargofijo obtenerFiseCargoFijo(FiseMcargofijoPK id) 
			throws SQLException;
	
	List<FiseMcargofijo> buscarFiseCargoFijo(String codEmpresa, Long anioRep,Long mesRep ) 
			throws SQLException;

}
