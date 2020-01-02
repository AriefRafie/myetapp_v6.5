package ekptg.model.htp.gadaian;

import java.util.Vector;

import ekptg.model.entities.Bebanan;
import ekptg.model.entities.MaklumatGadaian;

public interface IGadaian {
	public Bebanan cariBebanan(String idPermohonan)throws Exception ;
	
	public MaklumatGadaian cariMaklumat(String idPermohonan)throws Exception ;
	public Vector getSenaraiFailMengikutSubUrusan(
			String idSubUrusan, String tajuk, String noFail, String idNegeri) throws Exception ;
	public Vector getSemak(String idFail)throws Exception ;
	public Vector getPermohonans(String idFail,String noFail, String carian)throws Exception ;

}
