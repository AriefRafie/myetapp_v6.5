package ekptg.model.integrasi;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import etapp.entity.integrasi.MaklumatCukai;

public interface IIntegrasi {
	public String simpan(Hashtable hInsert) throws Exception;
	public String kemaskini(Hashtable hUpdate) throws Exception;
	public void hapus(String idHapus) throws Exception;
	public Vector getSenarai() throws Exception;
	//public Hashtable getMaklumat(String id) throws Exception;
	public MaklumatCukai getMaklumat(String id) throws Exception;
	public Vector carianPerintahHQProses(String role,String noFail, String namaPemohon, String namaSimati
			, String jenisKp, String noKp, HttpSession session) throws Exception;
	public Vector carianPerintahHQSemak(String role,String noFail, String namaPemohon, String namaSimati
			, String jenisKp, String noKp, HttpSession session) throws Exception;
	public Vector carianPerintahHQ(String role,String noFail, String namaPemohon, String namaSimati
			, String jenisKp, String noKp, HttpSession session,	boolean setLimit) throws Exception;
	public Vector carianPerintah(String role,String noFail, String namaPemohon, String namaSimati
			, String jenisKp, String noKp, HttpSession session) throws Exception;
	public Vector carianFail(String role,String noFail, String namaPemohon
			, String namaSimati, String jenisKp, String noKp
			, HttpSession session) throws Exception;

	
}
