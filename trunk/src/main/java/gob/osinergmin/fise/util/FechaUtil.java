package gob.osinergmin.fise.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class FechaUtil {
	
	public static final String[] MESES = 
		{"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Setiembre","Octubre","Noviembre","Diciembre"};
	public static final String[] DIAS = 
		{"Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"};
	
	private static DateFormat format_full = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");
	
	private static DateFormat format = new SimpleDateFormat(
			"dd/MM/yyyy");
	
	private static DateFormat formatHora = new SimpleDateFormat(
			"HH:mm:ss");
	
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
	
	public static String fechaHoyYYYYMMDD(){
    	StringBuilder sb = new StringBuilder();
    	Calendar c = Calendar.getInstance();
    	sb.append(c.get(Calendar.YEAR));
    	sb.append(c.get(Calendar.MONTH)<9?"0"+(c.get(Calendar.MONTH)+1):c.get(Calendar.MONTH)+1);
    	sb.append(c.get(Calendar.DAY_OF_MONTH)<10?"0"+c.get(Calendar.DAY_OF_MONTH):c.get(Calendar.DAY_OF_MONTH));
    	return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(fechaHoyYYYYMMDD());
		
		//System.out.println( Calendar.getInstance() );
		//System.out.println( cargarMapaMeses());
		
	}
	
	public static String mesLetras(String valor){	
		int numMes = Integer.valueOf(valor);
		String mes="";
		switch(numMes) {
		 case 1: 
			 mes= "Ene";
		     break;
		 case 2: 
			 mes= "Feb";
		     break;
		 case 3: 
			 mes= "Mar";
		     break;
		 case 4: 
			 mes ="Abr";
		     break;		
		 case 5: 
			 mes= "May";
		     break;
		 case 6: 
			 mes = "Jun";
		     break;
		 case 7: 
			 mes = "Jul";
		     break;
		 case 8: 
			 mes = "Ago";
		     break;
		 case 9: 
			 mes = "Sep";
		     break;		
		 case 10: 
			 mes ="Oct";
		     break;
		 case 11: 
			 mes = "Nov";
		     break;
		 case 12: 
			 mes = "Dic";
		     break;
		 default: 
			 mes = "Otro Año";
		     break;
		 }
		return mes;
	}
	
	public static Date getFechaStringToDate(String date) {
		Date fecha = null;
		try {
			fecha = format_full.parse(date);
			return fecha;
		} catch (Exception ex) {
			System.out.println("Error al parsear la fecha");
			return null;
		}
	}
	
	public static String getFechaDateToString(Date date) {
		String fecha = null;
		try {
			fecha = format.format(date);
			return fecha;
		} catch (Exception ex) {
			System.out.println("Error al parsear la fecha");
			return ""+date;
		}
	}
	
	public static String getHoraActual() {
	    Date ahora = new Date();	  
	    return formatHora.format(ahora);
	}
	
	//comparar fechas sin importar hora minuto segundo
	public static Date fechaDDMMYYYY(Date fecha){
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String formatoDDMMYYY = formato.format(fecha);
        Date fechaFinal = null;
        try {
        	fechaFinal = formato.parse(formatoDDMMYYY);
        } catch (java.text.ParseException e) {
        	e.printStackTrace();
        }
        return fechaFinal;
    }
	
	public static boolean comparaRangoFecha( Date fecha, Date fechaInicio, Date fechaFin ){
		boolean result = false;
		Date fechaAComparar = fechaDDMMYYYY(fecha);
		Date fechaInicial = fechaDDMMYYYY(fechaInicio);
		Date fechaFinal = fechaDDMMYYYY(fechaFin);
		if( (fechaAComparar.after(fechaInicial) || fechaAComparar.equals(fechaInicial)) 
				&& (fechaAComparar.before(fechaFinal) || fechaAComparar.equals(fechaFinal))  ){
			result = true;
		}
		return result;
	}
	
	public static String fecha_DD_MM_YYYY(Date fecha){
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String cadenaFecha = formato.format(fecha);
       return cadenaFecha.toString();
    }
	
}
