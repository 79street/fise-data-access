package gob.osinergmin.fise.gart.service;

import gob.osinergmin.fise.domain.FiseFormato14BC;
import gob.osinergmin.fise.domain.FiseFormato14BD;

import java.util.List;

public interface Formato14BGartService {
	
	List<FiseFormato14BC> listarFormato14BC();
	List<FiseFormato14BD> listarFormato14BD();

}
