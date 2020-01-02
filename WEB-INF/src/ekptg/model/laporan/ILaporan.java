package ekptg.model.laporan;

import java.util.Hashtable;
import java.util.Vector;

public interface ILaporan {
	public int getBilPerbicaraan(
		String idNegeri, String idUnit,String idPegawai, String tahun, String bulanMM) throws Exception ;
	public int getBilPerbicaraanSelesai(
		String idNegeri, String idUnit,String idPegawai, String tahun, String bulanMM) throws Exception ;
	public Vector<Hashtable<String,String>> getSenarai(
		String idNegeri, String idUnit,String idPegawai, String tahun, String bulanMM) throws Exception;
	public Vector<Hashtable<String,String>> getSenaraiSelesai(
		String idNegeri, String idUnit,String idPegawai, String tahun, String bulanMM) throws Exception;

}
