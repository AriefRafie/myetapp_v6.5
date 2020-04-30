package ekptg.model.utils;


import ekptg.model.entities.Tblrujdokumen;

public interface ILampiran {	
	
	public Tblrujdokumen getLampiran(String iDokumen) throws Exception;
	public Tblrujdokumen getLampiran(String iDokumen
		,String colNama,String colJenis,String colCont
		,String namaTab) throws Exception;
	public void simpanDokumenInt(Tblrujdokumen data) throws Exception;		

	
}
