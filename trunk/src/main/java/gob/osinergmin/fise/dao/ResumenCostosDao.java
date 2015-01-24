package gob.osinergmin.fise.dao;

import java.sql.SQLException;
import java.util.List;

public interface ResumenCostosDao {
	
	
	List<Object[]> listarResumenCostosF14A(String codEmpresa,
			Long idGrupoInf) throws SQLException;
	
	 List<Object[]> listarResumenCostosF12A(String codEmpresa,
				Long idGrupoInf) throws SQLException;
	 
	 List<Object[]> listarResumenCostosF12B(String codEmpresa,
				Long idGrupoInf) throws SQLException;
	 
	 List<Object[]> listarResumenCostosF14B(String codEmpresa,
				Long idGrupoInf) throws SQLException;
	 
	 List<Object[]> listarResumenCostosF14AComp(String codEmpresa,
				Long idGrupoInf,Long idZona) throws SQLException;
	 
	 List<Object[]> listarResumenCostosF14BComp(String codEmpresa,
				Long idGrupoInf,Long idZona) throws SQLException;

}
