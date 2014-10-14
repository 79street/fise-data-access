package gob.osinergmin.fise.constant;


public class FiseConstants {
	
	
	public final static String KEY_CFG_EXCEL_EXPORT = "CFG_XLS_EXPORT";
	public final static String BLANCO ="";
	public final static String SALTO_LINEA = "\n";
	
	public final static String ETAPA_SOLICITUD= "SOLICITUD";
	public final static String ETAPA_LEVOBS= "LEV.OBS";
	public final static String ETAPA_RECONSIDERACION= "RECONSIDERACION";
	public final static String ETAPA_RECONOCIDO= "RECONOCIDO";
	
	public final static long ZONABENEF_RURAL = 1;
	public final static long ZONABENEF_PROVINCIA = 2;
	public final static long ZONABENEF_LIMA = 3;
	
	public final static String COD_SAVE= "SAVE";
	public final static String COD_UPDATE= "UPDATE";
	public final static String COD_GET= "GET";
	public final static String COD_DELETE= "DELETE";
	
	public final static long ID_TABLA_FORMATO12A = 100;
	//-public final static String ID_TABLA_FORMATO12A = "100";
	
	public final static String TIPO_FORMATO_12 = "F12";
	public final static String TIPO_FORMATO_13 = "F13";
	public final static String TIPO_FORMATO_14 = "F14";
	
	public final static String NOMBRE_EXCEL_FORMATO12A = "Formatos12";
	public final static String NOMBRE_EXCEL_FORMATO13A = "Formatos13";
	public final static String NOMBRE_EXCEL_FORMATO14A = "Formatos14";
	
	//FORMATO 12 A
	public final static String TIPOARCHIVO_XLS = "1";
	public final static String TIPOARCHIVO_TXT = "2";
	public final static String FLAG_CARGAEXCEL_FORMULARIONUEVO = "2";
	public final static String FLAG_CARGAEXCEL_FORMULARIOMODIFICACION = "3";
	public final static String FLAG_CARGATXT_FORMULARIONUEVO = "4";
	public final static String FLAG_CARGATXT_FORMULARIOMODIFICACION = "5";
	public final static String NOMBRE_HOJA_FORMATO12A = "F12A";
	public final static int NRO_FILA_CODEMPRESA_FORMATO12A = 4;
	public final static int NRO_FILA_ANIOMES_FORMATO12A = 5;
	public final static int NRO_FILA_EMPAD_FORMATO12A = 12;
	public final static int NRO_FILA_AGENT_FORMATO12A = 16;
	public final static int NRO_FILA_DESPLPERSON_FORMATO12A = 19;
	public final static int NRO_FILA_ACTIVEXTR_FORMATO12A = 20;
	public final static int NRO_CELDA_EMPRESA = 5;
	public final static int NRO_CELDA_ANIO= 5;
	public final static int NRO_CELDA_MES = 6;
	public final static int NRO_CELDA_RURAL = 7;
	public final static int NRO_CELDA_PROVINCIA = 8;
	public final static int NRO_CELDA_LIMA = 9;
	public final static int NRO_MAX_FILAS = 3;
	public final static int TAMANIO_MAX_FILA = 159;
	//
	public final static String NOMBRE_COD_EMPRESA="COD_EMPRESA";
	public final static String NOMBRE_ANO_PRESENTACION="ANO_PRESENTACION";
	public final static String NOMBRE_MES_PRESENTACION="MES_PRESENTACION";
	public final static String NOMBRE_MES_EJECUCION="MES_EJECUCION_GASTO";
	public final static String NOMBRE_ZONA_BENEFICIARIO="ID_ZONA_BENEF";
	public final static String NOMBRE_NRO_EMPADRONADOS="NUMERO_EMPADRONADOS";
	public final static String NOMBRE_TOTAL_EMPADRONADOS="COSTO_TOTAL_EMPADRONAMIENTO";
	public final static String NOMBRE_NRO_AGENTES_AUTOR="NUMERO_AGENTES_AUTORIZ_GLP";
	public final static String NOMBRE_TOTAL_AGENTES="COSTO_TOTAL_GEST_RED_AG_GLP";
	public final static String NOMBRE_DESPLAZ_PERSONAL="TOTAL_DESPLAZAMIENTO_PERSONAL";
	public final static String NOMBRE_ACTIVID_EXTRAORD="TOTAL_ACTIVIDADES_EXTRAORD";
	
	public final static int LONGITUD_COD_EMPRESA = 4;
	public final static int LONGITUD_ANIO_PRESENTACION = 4;
	public final static int LONGITUD_MES_PRESENTACION = 2;
	public final static int LONGITUD_ANIO_EJECUCION = 4;
	public final static int LONGITUD_MES_EJECUCION = 2;
	public final static int LONGITUD_ZONA_BENEFICIARIO = 1;
	public final static int LONGITUD_NRO_EMPAD = 10;
	public final static int LONGITUD_COSTOUNIT_EMPAD = 18;
	public final static int LONGITUD_COSTOTOTAL_EMPAD = 18;
	public final static int LONGITUD_NRO_AGENT = 6;
	public final static int LONGITUD_COSTOUNIT_AGENT = 18;
	public final static int LONGITUD_COSTOTOTAL_AGENT = 18;
	public final static int LONGITUD_DESPLAZ_PERSONAL = 18;
	public final static int LONGITUD_ACTIVID_EXTRAORD = 18;
	public final static int LONGITUD_TOTAL_RECONOCER = 18;
	//
	public final static int POSICION_COD_EMPRESA = 4;
	public final static int POSICION_ANIO_PRESENTACION = 8;
	public final static int POSICION_MES_PRESENTACION = 10;
	public final static int POSICION_ANIO_EJECUCION = 14;
	public final static int POSICION_MES_EJECUCION = 16;
	public final static int POSICION_ZONA_BENEFICIARIO = 17;
	public final static int POSICION_NRO_EMPAD = 27;
	public final static int POSICION_COSTOUNIT_EMPAD = 45;
	public final static int POSICION_COSTOTOTAL_EMPAD = 63;
	public final static int POSICION_NRO_AGENT = 69;
	public final static int POSICION_COSTOUNIT_AGENT = 87;
	public final static int POSICION_COSTOTOTAL_AGENT = 105;
	public final static int POSICION_DESPLAZ_PERSONAL = 123;
	public final static int POSICION_ACTIVID_EXTRAORD = 141;
	public final static int POSICION_TOTAL_RECONOCER = 159;
	
	//parametros
	public final static String PARAM_DESC_EMPRESA_F12A = "DESC_EMPRESA";
	public final static String PARAM_ANO_PRES_F12A = "ANO_PRESENTACION";
	public final static String PARAM_DESC_MES_PRES_F12A = "DESC_MES_PRESENTACION";
	public final static String PARAM_ANO_EJEC_F12A = "ANO_EJECUCION";
	public final static String PARAM_DESC_MES_EJEC_F12A = "DESC_MES_PRESENTACION";
	public final static String PARAM_NRO_EMPAD_R_F12A = "NRO_EMPAD_R";
	public final static String PARAM_CU_EMPAD_R_F12A = "COSTO_UNIT_EMPAD_R";
	public final static String PARAM_CT_EMPAD_R_F12A = "COSTO_TOTAL_EMPAD_R";
	public final static String PARAM_NRO_AGENT_R_F12A = "NRO_AGENT_R";
	public final static String PARAM_CU_AGENT_R_F12A = "COSTO_UNIT_AGENT_R";
	public final static String PARAM_CT_AGENT_R_F12A = "COSTO_TOTAL_AGENT_R";
	public final static String PARAM_DESPL_PERS_R_F12A = "DESPL_PERSONAL_R";
	public final static String PARAM_ACTIV_EXTR_R_F12A = "ACTIV_EXTRAORD_R";
	public final static String PARAM_NRO_EMPAD_P_F12A = "NRO_EMPAD_P";
	public final static String PARAM_CU_EMPAD_P_F12A = "COSTO_UNIT_EMPAD_P";
	public final static String PARAM_CT_EMPAD_P_F12A = "COSTO_TOTAL_EMPAD_P";
	public final static String PARAM_NRO_AGENT_P_F12A = "NRO_AGENT_P";
	public final static String PARAM_CU_AGENT_P_F12A = "COSTO_UNIT_AGENT_P";
	public final static String PARAM_CT_AGENT_P_F12A = "COSTO_TOTAL_AGENT_P";
	public final static String PARAM_DESPL_PERS_P_F12A = "DESPL_PERSONAL_P";
	public final static String PARAM_ACTIV_EXTR_P_F12A = "ACTIV_EXTRAORD_P";
	public final static String PARAM_NRO_EMPAD_L_F12A = "NRO_EMPAD_L";
	public final static String PARAM_CU_EMPAD_L_F12A = "COSTO_UNIT_EMPAD_L";
	public final static String PARAM_CT_EMPAD_L_F12A = "COSTO_TOTAL_EMPAD_L";
	public final static String PARAM_NRO_AGENT_L_F12A = "NRO_AGENT_L";
	public final static String PARAM_CU_AGENT_L_F12A = "COSTO_UNIT_AGENT_L";
	public final static String PARAM_CT_AGENT_L_F12A = "COSTO_TOTAL_AGENT_L";
	public final static String PARAM_DESPL_PERS_L_F12A = "DESPL_PERSONAL_L";
	public final static String PARAM_ACTIV_EXTR_L_F12A = "ACTIV_EXTRAORD_L";
	
	public final static String PARAM_TOTAL_EMPAD_F12A = "TOTAL_EMPAD";
	public final static String PARAM_TOTAL_AGENT_F12A = "TOTAL_AGENT";
	public final static String PARAM_TOTAL_DESPLAZ_F12A = "TOTAL_DESPL_PERSONAL";
	public final static String PARAM_TOTAL_ACTIV_F12A = "TOTAL_ACTIV_EXTRAORD";
	public final static String PARAM_TOTAL_GENERAL_F12A = "TOTAL_GENERAL";
	
	//FORMATO 14 A
	

}
