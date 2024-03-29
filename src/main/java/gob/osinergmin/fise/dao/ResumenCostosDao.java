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
	 
	 List<Object[]> listarResumenCostosActividadF14A(String codEmpresa,
				Long idGrupoInf) throws SQLException;
	 
	 List<Object[]> listarResumenCostosActividadF14B(String codEmpresa,
				Long idGrupoInf) throws SQLException;
	 
	 List<Object[]> listarResumenCostosF14BRural(String codEmpresa,
				Long idGrupoInf) throws SQLException;
	 
	 List<Object[]> listarResumenCostosF14BProvincia(String codEmpresa,
				Long idGrupoInf) throws SQLException;
	 
	 List<Object[]> listarResumenCostosF14BLima(String codEmpresa,
				Long idGrupoInf) throws SQLException;
	 
	 List<Object[]> listarResumenCostosAprobadoF12A(String codEmpresa,
				Long idGrupoInf) throws SQLException;
	 
	 List<Object[]> listarResumenCostosAprobadoF12B(String codEmpresa,
				Long idGrupoInf) throws SQLException;
	 
	 List<Object[]> listarResumenCostosAprobadoF12AB(String codEmpresa,
				Long idGrupoInf) throws SQLException;

}
