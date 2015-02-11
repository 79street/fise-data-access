package gob.osinergmin.fise.util;

import gob.osinergmin.fise.constant.FiseConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Locale;

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
	
	//validar positivos
	public static boolean validarCampoLongEnteroPositivoTxt(String valor){
		boolean val=false;
		try {
			long m = Long.parseLong(valor);
			if( m>=0 ){
				val = true;
			}
		} catch (Exception e) {
			val = false;
		}
		return val;
	}
	
	public static boolean validarCampoBigDecimalPositivoTxt(String valor){
		boolean val=false;
		try {
			BigDecimal m = new BigDecimal(valor);
			if( m.compareTo(BigDecimal.ZERO) >=0 ){
				val = true;
			}
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
	
	public static String nombreIndividualActaRemision(String codEmpresa, Long anoPresentacion, Long mesPresentacion, String tipoFormato){
		String nombre = ""+anoPresentacion+mesPresentacion+FiseConstants.UNDERLINE+codEmpresa.trim()+FiseConstants.UNDERLINE+tipoFormato
				+FiseConstants.UNDERLINE+FiseConstants.SUFIJO_NOMBRE_INDIVIDUAL_ACTAREMISION+FiseConstants.EXTENSIONARCHIVO_PDF;
		return nombre;
	}
	
	public static String nombreIndividualFormato(String codEmpresa, Long anoPresentacion, Long mesPresentacion, String tipoFormato){
		String nombre = ""+anoPresentacion+mesPresentacion+FiseConstants.UNDERLINE+codEmpresa.trim()+FiseConstants.UNDERLINE+tipoFormato
				+FiseConstants.UNDERLINE+FiseConstants.SUFIJO_NOMBRE_INDIVIDUAL_FORMATO+FiseConstants.EXTENSIONARCHIVO_PDF;
		return nombre;
	}
	
	public static String nombreIndividualAnexoObs(String codEmpresa, Long anoPresentacion, Long mesPresentacion, String tipoFormato){
		String nombre = ""+anoPresentacion+mesPresentacion+FiseConstants.UNDERLINE+codEmpresa.trim()+FiseConstants.UNDERLINE+tipoFormato
				+FiseConstants.UNDERLINE+FiseConstants.SUFIJO_NOMBRE_INDIVIDUAL_ANEXOOBS+FiseConstants.EXTENSIONARCHIVO_PDF;
		return nombre;
	}
	
	public static String nombreIndividualActaRemisionGeneral(String codEmpresa, String tipoFormato){
		String nombre = ""+codEmpresa.trim()+FiseConstants.UNDERLINE+tipoFormato
				+FiseConstants.UNDERLINE+FiseConstants.SUFIJO_NOMBRE_INDIVIDUAL_ACTAREMISION+FiseConstants.EXTENSIONARCHIVO_PDF;
		return nombre;
	}
	
	//nombre txt
	public static String nombreArchivoCargaTxt(Long anoPresentacion, Long mesPresentacion, String codEmpresa, String tipoFormato){
		String nombre = ""+anoPresentacion+FormatoUtil.rellenaIzquierda(""+mesPresentacion, '0', 2)+FiseConstants.UNDERLINE+codEmpresa.trim()+FiseConstants.UNDERLINE+tipoFormato
				+FiseConstants.EXTENSIONARCHIVO_TXT;
		return nombre;
	}
	
	public static String eliminaDecimales(String string){
        if(string != null && string.indexOf(".") > 0){
            return string.substring(0, string.indexOf("."));
        }else{
            return string;
        }
    }
    public static String eliminaPuntoDecimalToString(String string){
        if(string != null && string.indexOf(".") > 0){
            return string.substring(0, string.indexOf("."))+ string.substring(string.indexOf(".")+1, string.length());
        }else{
            return string;
        }
    }
    
    public static String  numeroSinPuntoDecimal(String numero){
    	numero = numero.replace(".", "");
    	return numero;
    }
    
    public static String conversion(double valor){
        Locale.setDefault(Locale.US);
        DecimalFormat num = new DecimalFormat("###########.00");
        return num.format(valor);
      }
    
    public static String cambiaTextoAMinusculas(String cadena, int tipo){
    	//tipo: 0 - Titulo
    	//tipo: 1 - Subtitulo
    	String cadenaRetorno = "";
    	if(cadena!=null){
    		if( !FiseConstants.BLANCO.equals(cadena) ){
        		if(tipo==0){
            		cadenaRetorno = cadena.substring(0,1)+cadena.substring(1,cadena.length()).toLowerCase();
            	}else if(tipo==1){
            		cadenaRetorno = cadena.toLowerCase();
            	}
        	}
    	}
    	return cadenaRetorno;
    }
    
    //validaciones de estructura de campos
    public static boolean validaCampoNumeroDecimal(BigDecimal campo, int longitudEnteraMax, int longitudDecimalMax ){
		boolean retorno=false;
		String parteEntera = "";
		String parteDecimal = "";
		if( campo!=null ){
			parteEntera = parteEntera+campo.setScale(0,RoundingMode.DOWN);
			parteDecimal = parteDecimal+campo.subtract(campo.setScale(0, RoundingMode.FLOOR)).movePointRight(campo.scale());
			if( parteEntera.length()<=longitudEnteraMax && parteDecimal.length()<=longitudDecimalMax ){
				retorno = true;
			}else{
				retorno = false;
			}
		}
		return retorno;
	}
	
	public static boolean validaCampoNumeroEntero(Long campo, int longitudEntera){
		boolean retorno=false;
		String parteEntera = "";
		if( campo!=null ){
			parteEntera = parteEntera+campo;
			if( parteEntera.length()<=longitudEntera ){
				retorno = true;
			}else{
				retorno = false;
			}
		}
		return retorno;
	}
	
	public static boolean validaCampoString(String campo, int longitudMax){
		boolean retorno=false;
		if( campo!=null ){
			if( campo.length()<=longitudMax ){
				retorno = true;
			}else{
				retorno = false;
			}
		}
		return retorno;
	}
	
}
