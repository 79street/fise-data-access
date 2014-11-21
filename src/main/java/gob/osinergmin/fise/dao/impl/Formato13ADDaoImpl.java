package gob.osinergmin.fise.dao.impl;

import gob.osinergmin.base.dao.impl.GenericDaoImpl;
import gob.osinergmin.fise.bean.Formato13ADReportBean;
import gob.osinergmin.fise.dao.Formato13ADDao;
import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13AD;
import gob.osinergmin.fise.util.FormatoUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "formato13ADDaoImpl")
public class Formato13ADDaoImpl extends GenericDaoImpl implements Formato13ADDao {

	public List<FiseFormato13AD> listarFormato13ADByFormato13AC(FiseFormato13AC formato13AC) {
		List<FiseFormato13AD> lista = null;
		try {
			String q = "SELECT t FROM FiseFormato13AD t WHERE 1=1 ";
			if (FormatoUtil.isNotBlank(formato13AC.getId().getCodEmpresa())) {
				q = q + " AND t.id.codEmpresa = :codEmpresa ";
			}
			if (formato13AC.getId().getAnoPresentacion() != 0) {
				q = q + " AND t.id.anoPresentacion = :anioPresentacion ";
			}
			if (formato13AC.getId().getMesPresentacion() != 0) {
				q = q + " AND t.id.mesPresentacion = :mesPresentacion ";
			}
			if (FormatoUtil.isNotBlank(formato13AC.getId().getEtapa())) {
				q = q + " AND t.id.etapa = :etapa ";
			}
			Query query = em.createQuery(q);
			if (FormatoUtil.isNotBlank(formato13AC.getId().getCodEmpresa())) {
				query.setParameter("codEmpresa", formato13AC.getId().getCodEmpresa());
			}
			if (formato13AC.getId().getAnoPresentacion() != 0) {
				query.setParameter("anioPresentacion", formato13AC.getId().getAnoPresentacion());
			}
			if (formato13AC.getId().getMesPresentacion() != 0) {
				query.setParameter("mesPresentacion", formato13AC.getId().getMesPresentacion());
			}
			if (FormatoUtil.isNotBlank(formato13AC.getId().getEtapa())) {
				query.setParameter("etapa", formato13AC.getId().getEtapa());
			}

			lista = query.getResultList();
			System.out.println("SQL   > " + q);
			/*
			 * if(lista==null){ return Collections.EMPTY_LIST; }else{ return
			 * lista; }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lista;
	}

	public List<Formato13ADReportBean> listarLocalidadesPorZonasBenefFormato13ADByFormato13AC(FiseFormato13AC formato13AC) {

		List<Formato13ADReportBean> lst = new ArrayList<Formato13ADReportBean>();
		try {
			StringBuilder sql = new StringBuilder();

			sql.append(" SELECT COD_UBIGEO,ID_ZONA_BENEF,ANO_ALTA,MES_ALTA,DESCRIPCION_LOCALIDAD,NOMBRE_SEDE_ATIENDE, ");
			sql.append(" SUM(DECODE(COD_SECTOR_TIPICO,'1  ',NUMERO_BENEFI_POTE_SECT_TIPICO,0)) ST_1, ");
			sql.append(" SUM(DECODE(COD_SECTOR_TIPICO,'2  ',NUMERO_BENEFI_POTE_SECT_TIPICO,0)) ST_2, ");
			sql.append(" SUM(DECODE(COD_SECTOR_TIPICO,'3  ',NUMERO_BENEFI_POTE_SECT_TIPICO,0)) ST_3, ");
			sql.append(" SUM(DECODE(COD_SECTOR_TIPICO,'4  ',NUMERO_BENEFI_POTE_SECT_TIPICO,0)) ST_4, ");
			sql.append(" SUM(DECODE(COD_SECTOR_TIPICO,'5  ',NUMERO_BENEFI_POTE_SECT_TIPICO,0)) ST_5, ");
			sql.append(" SUM(DECODE(COD_SECTOR_TIPICO,'6  ',NUMERO_BENEFI_POTE_SECT_TIPICO,0)) ST_6, ");
			sql.append(" SUM(DECODE(COD_SECTOR_TIPICO,'SER',NUMERO_BENEFI_POTE_SECT_TIPICO,0)) ST_SER, ");
			sql.append(" SUM(DECODE(COD_SECTOR_TIPICO,'ESP',NUMERO_BENEFI_POTE_SECT_TIPICO,0)) ESPECIAL, ");
			sql.append(" ANO_INICIO_VIGENCIA, ANO_FIN_VIGENCIA ");
			sql.append(" FROM FISE.FISE_FORMATO_13A_D ");
			sql.append(" WHERE 1=1 ");

			if (FormatoUtil.isNotBlank(formato13AC.getId().getCodEmpresa()))
				sql.append(" AND COD_EMPRESA = '").append(formato13AC.getId().getCodEmpresa().trim()).append("' ");
			if (formato13AC.getId().getAnoPresentacion() != 0)
				sql.append(" AND ANO_PRESENTACION = ").append(formato13AC.getId().getAnoPresentacion());
			if (formato13AC.getId().getMesPresentacion() != 0)
				sql.append(" AND MES_PRESENTACION = ").append(formato13AC.getId().getMesPresentacion());
			if (FormatoUtil.isNotBlank(formato13AC.getId().getEtapa()))
				sql.append(" AND ETAPA = '").append(formato13AC.getId().getEtapa().trim()).append("' ");

			sql.append(" GROUP BY COD_UBIGEO, ID_ZONA_BENEF, ANO_ALTA,MES_ALTA,DESCRIPCION_LOCALIDAD,NOMBRE_SEDE_ATIENDE, ");
			sql.append(" ANO_INICIO_VIGENCIA, ANO_FIN_VIGENCIA ");

			Query query = em.createNativeQuery(sql.toString());

			List resultado = query.getResultList();
			Iterator it = resultado.iterator();
			while (it.hasNext()) {
				Object[] valor = (Object[]) it.next();
				Formato13ADReportBean objeto = new Formato13ADReportBean();
				objeto.setCodUbigeo((String) valor[0]);
				objeto.setIdZonaBenef(((BigDecimal) valor[1]).longValue());
				objeto.setAnioAlta(((BigDecimal) valor[2]).longValue());
				objeto.setMesAlta(((BigDecimal) valor[3]).longValue());
				objeto.setDescLocalidad((String) valor[4]);
				objeto.setNombreSedeAtiende((String) valor[5]);
				objeto.setNroBenefPoteSecTipico1(((BigDecimal) valor[6]).longValue());
				objeto.setNroBenefPoteSecTipico2(((BigDecimal) valor[7]).longValue());
				objeto.setNroBenefPoteSecTipico3(((BigDecimal) valor[8]).longValue());
				objeto.setNroBenefPoteSecTipico4(((BigDecimal) valor[9]).longValue());
				objeto.setNroBenefPoteSecTipico5(((BigDecimal) valor[10]).longValue());
				objeto.setNroBenefPoteSecTipico6(((BigDecimal) valor[11]).longValue());
				objeto.setNroBenefPoteSecTipico7(((BigDecimal) valor[12]).longValue());
				objeto.setNroBenefPoteSecTipico8(((BigDecimal) valor[13]).longValue());
				//add
				objeto.setAnioInicioVigencia(((BigDecimal)valor[14])!=null?((BigDecimal)valor[14]).longValue():0);
				objeto.setAnioFinVigencia(((BigDecimal)valor[15])!=null?((BigDecimal)valor[15]).longValue():0);
				//
				lst.add(objeto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return lst;
	}

	@Override
	@Transactional
	public FiseFormato13AD savedetalle(FiseFormato13AD fiseD) throws DataIntegrityViolationException{
		FiseFormato13AD result = null;
		try {

			em.persist(fiseD);
			result = fiseD;
		}  finally {
			em.close();

		}

		return result;
	}
	
	@Override
	@Transactional
	public FiseFormato13AD updatedetalle(FiseFormato13AD fiseD) {
		FiseFormato13AD result = null;
		try {
			em.merge(fiseD);
			result = fiseD;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public Integer deletedetalle(String emp, Long anio, Long mes, String etapa) {
		int result=-1;
		try{
			
			StringBuffer q=new StringBuffer();
			q.append("DELETE FROM FiseFormato13AD t where 1=1 ");
			
			
				if(emp !=null){
					q.append(" and t.id.codEmpresa = :emp");
				}if(anio!=null){
					q.append(" and t.id.anoPresentacion = :anio");
				}if(mes!=null){
					q.append(" and t.id.mesPresentacion = :mes");
				}if(etapa!=null){
					q.append(" and t.id.etapa = :etp");
				}
				
			
			Query query = em.createQuery(q.toString());
			
			
			if(emp !=null){
					query.setParameter("emp", emp);
			}if(anio!=null){
					query.setParameter("anio", anio);
			}if(mes!=null){
					query.setParameter("mes", mes);
			}if(etapa!=null){
					query.setParameter("etp", etapa);
				}
				
			
			
			result= query.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
		return result;
	}
	
	@Override
	public void eliminarFormato13AD(FiseFormato13AD fiseFormato13AD){
		try{
			//verificar si la entidad pertenece a la transaccion o no
			em.remove(em.contains(fiseFormato13AD) ? fiseFormato13AD : em.merge(fiseFormato13AD));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			 em.close();
		 }
	}

	

}
