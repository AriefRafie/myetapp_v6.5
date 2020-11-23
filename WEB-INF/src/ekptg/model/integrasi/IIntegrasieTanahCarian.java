package ekptg.model.integrasi;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import etapp.entity.integrasi.MaklumatCukai;

public interface IIntegrasieTanahCarian {
	public Hashtable<String,String>setMaklumatHakmilik(String idMT) throws Exception;
	public Vector getBeanMaklumatHakmilik() ;
	public void daftarHakmilik(String idHakmilik, String noResit, String idHakmilikInt, String idPermohonan,String userId) 
		throws Exception;

}
