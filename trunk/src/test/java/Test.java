import gob.osinergmin.fise.domain.FiseFormato12AC;
import gob.osinergmin.fise.gart.service.Formato12AGartService;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })  
//@Transactional  
//@TransactionConfiguration(defaultRollback=true)  
public class Test {

		@Autowired
		private Formato12AGartService formatoService;

		// Método con el test unitario, identificado con la anotación @Test.
		@org.junit.Test
		public void testGetSHA1(){
			List<FiseFormato12AC> lista = formatoService.listarFormato12AC();
			System.out.println("sdsdsd"+lista);

		}

}
