package gob.osinergmin.fise.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class FechaUtil {
	
	public static final String[] MESES = 
		{"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Setiembre","Octubre","Noviembre","Diciembre"};
	public static final String[] DIAS = 
		{"Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"};
	
	public static Map<Long,String> cargarMapaMeses() {
		
		Map<Long,String> mapaInicial=new HashMap<Long,String>();
		//Map<String,String> mapaMeses=new HashMap<String,String>();
		
		for(int i=0;i<MESES.length;i++){
			mapaInicial.put(new Long(i+1),MESES[i]);
		}
		/*System.out.println(mapaInicial);
		//Map<Integer,String> mapaFinal=sortByKeys(mapaInicial);
		//System.out.println(mapaFinal);
		//Iterator it = mapaFinal.entrySet().iterator();
		Iterator it = mapaInicial.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			mapaMeses.put(String.valueOf(e.getKey()),e.getValue().toString());
		}
		System.out.println(mapaMeses);*/
		return mapaInicial;
	}

	/*public static <K extends Comparable,V extends Comparable> Map<K,V> sortByKeys(Map<K,V> map){
        List<K> keys = new LinkedList<K>(map.keySet());
        Collections.sort(keys);
      
        //LinkedHashMap will keep the keys in the order they are inserted
        //which is currently sorted on natural ordering
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
        for(K key: keys){
            sortedMap.put(key, map.get(key));
        }
      
        return sortedMap;
    }*/
	
	//
	public static String obtenerNroAnioFechaActual(){
		Calendar hoy = Calendar.getInstance();
		return String.valueOf(hoy.get(Calendar.YEAR));
	}

	public static String obtenerNroMesFechaActual(){
		Calendar hoy = Calendar.getInstance();
		return String.valueOf(hoy.get(Calendar.MONTH)+1);
	}
	
	public static Date obtenerFechaActual(){
		return new Date();
	}
	
	public static void main(String[] args) {
		//System.out.println(horaActualHHMMSS());
		
		System.out.println( Calendar.getInstance() );
		System.out.println( cargarMapaMeses());
		
	}
	
}
