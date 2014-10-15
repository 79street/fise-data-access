package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.dao.FisePeriodoEnvioDao;
import gob.osinergmin.fise.domain.FisePeriodoEnvio;
import gob.osinergmin.fise.util.FechaUtil;
import gob.osinergmin.fise.util.FormatoUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository(value = "fisePeriodoEnvioDaoImpl")
public class FisePeriodoEnvioDaoImpl extends GenericDaoImpl implements FisePeriodoEnvioDao {
	
	public List<FisePeriodoEnvio> listarFisePeriodoEnvioMesAnioEtapa(String codEmpresa, String nombreFormato) {
		
		List<FisePeriodoEnvio> lst = new ArrayList<FisePeriodoEnvio>();
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append(" SELECT ANO_PRESENTACION || LPAD(MES_PRESENTACION, 2, '0') || ETAPA CODIGO,");
			sql.append(" DECODE(MES_PRESENTACION,1,'Ene',2,'Feb',3,'Mar',4,'Abr',5,'May',6,'Jun',7,'Jul',8,'Ago',9,'Set',10,'Oct',11,'Nov',12,'Dic')");
			sql.append(" || '-' || ANO_PRESENTACION || ' / ' || ETAPA DESCRIPCION");
			sql.append(" FROM FISE.FISE_PERIODO_ENVIO t");
			sql.append(" WHERE 1=1 ");
			
			if(FormatoUtil.isNotBlank(codEmpresa))
				sql.append(" AND COD_EMPRESA = '").append(codEmpresa.trim()).append("' ");
			if(FormatoUtil.isNotBlank(nombreFormato))
				sql.append(" AND FORMATO = '").append(nombreFormato.trim()).append("' ");
			
			sql.append(" AND '").append(FechaUtil.fechaHoyYYYYMMDD().trim()).append("' BETWEEN TO_CHAR(DESDE, 'YYYYMMDD') AND TO_CHAR(hasta, 'YYYYMMDD') ");
			
			//sql.append(" ORDER BY ordenador");
			//System.out.println("QUERY:"+sql.toString());
			
			Query query = em.createNativeQuery(sql.toString());
			
			List resultado = query.getResultList();
			Iterator it=resultado.iterator();
			while(it.hasNext()){
				Object[] valor=(Object[] )it.next();
				FisePeriodoEnvio periodoEnvio=new FisePeriodoEnvio();
				periodoEnvio.setCodigoItem((String)valor[0]);
				periodoEnvio.setDescripcionItem((String)valor[1]);
				lst.add(periodoEnvio);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return lst;
	}

}
