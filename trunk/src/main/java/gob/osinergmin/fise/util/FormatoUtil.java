package gob.osinergmin.fise.util;

import java.math.BigDecimal;
import java.util.Arrays;

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
	
	//validacion txt
	public static boolean validarCampoLongTxt(String valor){
		boolean val=false;
		try {
			Long.parseLong(valor);
			val = true;
		} catch (Exception e) {
			val = false;
		}
		return val;
	}
	
	public static boolean validarCampoBigDecimalTxt(String valor){
		boolean val=false;
		try {
			new BigDecimal(valor);
			val = true;
		} catch (Exception e) {
			val = false;
		}
		return val;
	}
	
	//concatenar n arrays
	public static <T> T[] concatAll(T[] first, T[]... rest) {
		  int totalLength = first.length;
		  for (T[] array : rest) {
		    totalLength += array.length;
		  }
		  T[] result = Arrays.copyOf(first, totalLength);
		  int offset = first.length;
		  for (T[] array : rest) {
		    System.arraycopy(array, 0, result, offset, array.length);
		    offset += array.length;
		  }
		  return result;
	}
	
	public static byte[] concatAllBytes(byte[] first, byte[]... rest) {
		  int totalLength = first.length;
		  for (byte[] array : rest) {
		    totalLength += array.length;
		  }
		  byte[] result = Arrays.copyOf(first, totalLength);
		  int offset = first.length;
		  for (byte[] array : rest) {
		    System.arraycopy(array, 0, result, offset, array.length);
		    offset += array.length;
		  }
		  return result;
	}
	
}