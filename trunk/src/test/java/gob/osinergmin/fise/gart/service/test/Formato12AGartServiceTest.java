package gob.osinergmin.fise.gart.service.test;
import gob.osinergmin.fise.domain.AdmEmpresa;
import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.domain.FiseFormato12AD;
import gob.osinergmin.fise.gart.service.AdmEmpresaGartService;
import gob.osinergmin.fise.gart.service.Formato12AGartService;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })  
//@Transactional  
//@TransactionConfiguration(defaultRollback=true)  
public class Formato12AGartServiceTest {

	
		@Autowired
		private Formato12AGartService formatoService;

		@Autowired
		private AdmEmpresaGartService empresaService;
		// Método con el test unitario, identificado con la anotación @Test.
		@org.junit.Test
		public void testGetSHA1(){
			List<FiseFormato12AC> lista = formatoService.listarFormato12AC();
			System.out.println("sdsdsd"+lista);
			//List<FiseFormato12AD> lista2 = formatoService.;
			/*int i=0;
			i = formatoService.obtenerSecuencia();
			System.out.println(i);
			
			FiseFormato12AC fo = new FiseFormato12AC();
			FiseFormato12ACPK pk = new FiseFormato12ACPK();
			pk.setCodEmpresa("ADIL");
			pk.setAnoPresentacion(2014);
			pk.setMesPresentacion(10);
			pk.setAnoEjecucionGasto(2014);
			pk.setMesEjecucionGasto(10);
			pk.setEtapa("SOLICITUD");
			fo = formatoService.obtenerFormato12ACByPK(pk);
			Formato12ACBean bean = formatoService.estructurarFormato12ABeanByFiseFormato12AC(fo);
			System.out.println(bean);*/
			
			
		}
		
		@org.junit.Test
		public void testEmpresasRem(){
			List<AdmEmpresa> lista = empresaService.getEmpresaFise("FISE", "REMISION", "'EDLN'");
			if(lista!=null){
				System.out.println("Tamanio lista:"+lista.size());
				//List<FiseFormato12AD> lista2 = formatoService.;
				for (AdmEmpresa admEmpresa : lista) {
					System.out.println("VALOR:"+admEmpresa.getCodEmpresa()+" / "+admEmpresa.getDscEmpresa());
				}
			}
		}

}
