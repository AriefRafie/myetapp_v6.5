package ekptg.model.utils.lampiran;


import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import ekptg.model.entities.Tblrujdokumen;

public interface ILampiran {	
	public String javascriptUpload(String jsUpload,String jsPapar
			,String idDokumen,HttpSession session) throws Exception;
	public String getLampirans(String idRujukan,String jenisDokumen,String js) throws Exception;
	public Tblrujdokumen getLampiran(String iDokumen) throws Exception;
	public Tblrujdokumen getLampiran(String iDokumen
		,String colNama,String colJenis,String colCont
		,String namaTab) throws Exception;
	public Vector<Tblrujdokumen> getLampirans(String idRujukan,String jenis) throws Exception;			

	public void hapus(String idDokumen) throws Exception;
	public void simpan(FileItem item,HttpServletRequest request) throws Exception;

	public void hapus(String idDokumen) throws Exception;
	public void simpanDokumenInt(Tblrujdokumen data) throws Exception;		
	public void simpan(FileItem item,HttpServletRequest request) throws Exception;
	
}
