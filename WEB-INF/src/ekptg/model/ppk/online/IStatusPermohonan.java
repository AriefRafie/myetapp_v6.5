package ekptg.model.ppk.online;

import java.util.Vector;

public interface IStatusPermohonan {
	public Vector<StatusPermohonanPPK> getPermohonanList(String noKP);
	public Vector<StatusPermohonanPPK> getPermohonanSiMatiList(String noKPSiMati);
	public boolean isSeksyen17Selesai(String kpbarusimati, String kplamasimati, String kplainsimati, 
	 String noPermohonansimati, String userid);
	public Vector getSeksyen17Permohonan(String kpbarusimati, String kplamasimati,String kplainsimati, String noPermohonansimati, String userid);
}
