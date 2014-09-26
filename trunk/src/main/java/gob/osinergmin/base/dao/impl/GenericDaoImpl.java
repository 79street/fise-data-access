package gob.osinergmin.base.dao.impl;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

public class GenericDaoImpl {
	
	protected EntityManager em;

	public GenericDaoImpl() {
	}

	@Transactional
	public void save(Object object) {
		System.out.println("entro a guardar");
		try {		
				em.persist(object);
				em.flush();			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Transactional
	public void update(Object object) {
		System.out.println("entro a actualizar");
		try {		
			em.merge(object); // Actualizacion
			em.flush();	
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Transactional
	public void delete(Object object) {		
		try {			
			em.remove(object);
			em.flush();	
		} catch (RuntimeException re) {
			re.printStackTrace();
		}
	}
	
}
