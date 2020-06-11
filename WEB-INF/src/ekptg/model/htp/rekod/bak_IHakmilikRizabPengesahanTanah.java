package ekptg.model.htp.rekod;

import java.util.Hashtable;

import ekptg.model.htp.entity.HtpPermohonan;


public interface bak_IHakmilikRizabPengesahanTanah{

	 public Hashtable<String, Comparable> getPerolehanInfo(String idHakmilik) throws Exception ;
	 public Hashtable<String, Comparable> getPerolehanInfo(String idHakmilik,String idHakmilik1)throws Exception ;
	 public HtpPermohonan findPermohonan(String idPermohonan) throws Exception ;
	 public String simpanPermohonanHTP(Hashtable<?, ?> data) throws Exception ;

	
	
}	
