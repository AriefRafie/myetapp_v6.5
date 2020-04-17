package ekptg.model.ppk.perintah;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

public interface IMaklumatPerintah {
	public Vector<Hashtable<String,String>> carianFail_semakanPerintahHQ(String role,String noFail, String namaPemohon
			, String namaSimati, String jenisKp, String noKp, HttpSession session) throws Exception;
	public Vector<Hashtable<String,String>> carianFail(String noFail, String namaPemohon
			, String namaSimati, String jenisKp, String noKp, HttpSession session) throws Exception ;

}
