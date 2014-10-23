package gob.osinergmin.base.dao;

public interface GenericDao {
	
	public void save(Object object);
    
	public void update(Object object); 
    
    public void delete(Object persistentInstance);

}
