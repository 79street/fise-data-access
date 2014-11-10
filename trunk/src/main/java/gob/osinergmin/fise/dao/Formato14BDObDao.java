package gob.osinergmin.fise.dao;

import gob.osinergmin.fise.domain.FiseFormato14BD;
import gob.osinergmin.fise.domain.FiseFormato14BDOb;

import java.util.List;

public interface Formato14BDObDao {
	
	List<FiseFormato14BDOb> listarFormato14BDObByFormato14BD(FiseFormato14BD formato14BD);
	void eliminarFormato14BDOb(FiseFormato14BDOb fiseFormato14BDOb);

}
