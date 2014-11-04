package gob.osinergmin.fise.gart.service.test;
import java.util.List;

import gob.osinergmin.fise.domain.FiseFormato13AC;
import gob.osinergmin.fise.domain.FiseFormato13ACPK;
import gob.osinergmin.fise.domain.FiseFormato13AD;
import gob.osinergmin.fise.gart.service.Formato13AGartService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })   
public class Formato13AGartServiceTest {

	
		@Autowired
		private Formato13AGartService formatoService;
		
		@Test
		public void buscarEmpresaTest(){
			String codEmpresa="";
			long anioDesde=0;
			long mesDesde=0;
			long anioHasta=0;
			long mesHasta=0;
			String etapa="";
			
			List<FiseFormato13AC> formatos=formatoService.buscarFormato13AC(codEmpresa, anioDesde, mesDesde, anioHasta, mesHasta, etapa);
			
			if(formatos!=null){
				for (FiseFormato13AC fiseFormato13AC : formatos) {
					System.out.println("EMPRESA:"+fiseFormato13AC.getId().getCodEmpresa());
				}
			}
		}
		
		@Test
		public void buscarDetalleFormatoTest(){
			String codEmpresa="";
			long anioPresentacion=0;
			long mesPresentacion=0;
			String etapa="";
			
			FiseFormato13AC formato13AC=new FiseFormato13AC();
			formato13AC.setDescEmpresa("");
			formato13AC.setId(new FiseFormato13ACPK());
			formato13AC.getId().setAnoPresentacion(0);
			formato13AC.getId().setMesPresentacion(0);;
			formato13AC.getId().setEtapa("");;
			System.out.println("FORMATO DETALLE");
			List<FiseFormato13AD> formatos=formatoService.listarFormato13ADByFormato13AC(formato13AC);
			
			if(formatos!=null){
				for (FiseFormato13AD fiseFormato13AD : formatos) {
					System.out.println("EMPRESA:"+fiseFormato13AD.getId().getCodEmpresa());
				}
			}
		}
		
}
