package ekptg.model.utils;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public interface IUserPegawai {

	// class IUserPegawai
	// test 13/8/2020

	public boolean delete(String id);
	public boolean insert(Hashtable<?, ?> parameters,String uid);
	public boolean update(Hashtable<?, ?> parameters,String id);

	public Hashtable<String, String> getSenaraiPegawai(String idPegawai) throws Exception;
	public Hashtable<String, String> getPengguna(String idPengguna) throws Exception;

	public List<Map<String,String>> getPenggunaMengikutRole(String role,String idRujukan) throws Exception;

	public Vector<Hashtable<String, String>> getSenaraiPegawai(String idSeksyen,String idSuburusan,String idNegeri)
		throws Exception ;
	public Vector<Hashtable<String, String>> getSenaraiPegawai(int idNegeri
		,String idUnit,String tahun)	throws Exception;
	public Vector<Hashtable<String, String>> getSenaraiPegawaiMapping(String idNegeri) throws Exception;
	public Vector<Hashtable<String, String>> getSenaraiUsersByNegeri(String idNegeri) throws Exception;

	//public HakmilikUrusan simpanHakmilik(HakmilikUrusan urusan);
	//public HakmilikUrusan updateHakmilik(HakmilikUrusan urusan);
	//public void deleteHakmilik(String idHakmilikUrusan) throws Exception;
}

