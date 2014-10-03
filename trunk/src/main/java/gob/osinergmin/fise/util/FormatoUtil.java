package gob.osinergmin.fise.util;

public class FormatoUtil {
	
	public static String rellenaIzquierda(String cadena, char relleno, int longitud) {
		if (cadena == null) {
			return null;
		}
		if (cadena.length() >= longitud) {
			return cadena;
		}
		StringBuffer resultado = new StringBuffer();
		int pad = longitud - cadena.length();
		for (int i = 1; i <= pad; i++) {
			resultado.append(relleno);
		}
		resultado.append(cadena);
		return resultado.toString();
	}

	public static String rellenaDerecha(String cadena, char relleno, int longitud) {
		if (cadena == null) {
			return null;
		}
		if (cadena.length() >= longitud) {
			return cadena;
		}
		StringBuffer resultado = new StringBuffer();
		resultado.append(cadena);
		int pad = longitud - cadena.length();
		for (int i = 1; i <= pad; i++) {
			resultado.append(relleno);
		}
		return resultado.toString();
	}

	public static boolean isBlank(String str)
	{
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
  
	public static boolean isNotBlank(String str)
	{
		return !isBlank(str);
	}
	
}
