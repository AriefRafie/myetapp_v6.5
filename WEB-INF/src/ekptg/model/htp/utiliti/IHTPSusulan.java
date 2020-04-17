package ekptg.model.htp.utiliti;

import java.util.Hashtable;
import java.util.Vector;

public interface IHTPSusulan {	
	
	public Hashtable<String, String> getMaklumat(String idPermohonan,String idSusulanStatus) throws Exception ;
	public Hashtable<String, String> getMaklumat(String idPermohonan,String sumber,String idSumber) throws Exception ;
	public Hashtable<String, String> getMaklumatSusulan(String idPermohonan,String sumber) throws Exception;
	public String getLangkahMengikutRole(String portal_role) throws Exception ;
	public String kemaskini(Hashtable<String, String> data) throws Exception ;
	public String simpan(Hashtable<String, String> data) throws Exception ;
	public String simpanKemaskini(Hashtable<String, String> data) throws Exception ;
	public String simpanSusulanStatusFail(Hashtable<String, String> data) throws Exception ;
	public Vector<Hashtable<String,String>> getListImage(String idHakmilikPerihal) throws Exception ;
	public Vector<Hashtable<String,String>> getMaklumat(String idPermohonan) throws Exception ;
	public Vector<Hashtable<String,String>> getMaklumatMengikutLangkah(String idPermohonan,String langkah) throws Exception ;
	public void hapus(String id) throws Exception ;
	//public String simpan(Hashtable data,String sumber) throws Exception ;
	//public String simpanSusulanStatusFail(Hashtable data,String sumber) throws Exception ;


}
