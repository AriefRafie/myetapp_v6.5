package ekptg.model.htp.gadaian;

import ekptg.model.entities.Bebanan;
import ekptg.model.entities.MaklumatGadaian;

public interface IGadaianPelepasan {
	public Bebanan cariBebanan(String idPermohonan)throws Exception;
	
	public MaklumatGadaian cariMaklumat(String idPermohonan)throws Exception;
	
}
