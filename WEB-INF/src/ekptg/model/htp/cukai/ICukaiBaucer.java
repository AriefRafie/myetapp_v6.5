package ekptg.model.htp.cukai;

import ekptg.model.htp.cukai.entity.BaucerCukai;

public interface ICukaiBaucer {
	
	public BaucerCukai getSenaraiBaucer(String idNegeri, String idPeringkatBayaran
			, String peringkatBayaran,String tahun,String idBaucer) throws Exception;
	public void hapusBaucerCukai(String pk) throws Exception;
	public boolean isBaucer(String idBaucer)throws Exception;
	public Double getJumlahBaucer()throws Exception ;

}	
