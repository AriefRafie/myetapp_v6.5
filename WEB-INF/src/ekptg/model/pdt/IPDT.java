package ekptg.model.pdt;

import java.util.Hashtable;
import java.util.Vector;

public interface IPDT {	
	
	 public String tambah(Hashtable<?, ?> data) throws Exception;
	 public String getIdTag();
	 public Vector<Hashtable<String, Comparable>> getCarian(String idAkta, String maklumat,String tempatBicara
			 , String tarikhKeputusan,  String tag) throws Exception;
	 public Hashtable getMaklumat(String idPerundangan) throws Exception;
	 public void kemaskini(Hashtable data) throws Exception;
	 public Vector getLampirans(String id_)throws Exception;
	 public void hapusLampiran(String idLampiran) throws Exception;
	 public void hapus(String idDokumen) throws Exception;

}
