package ekptg.model.htp;

import java.util.Vector;

import ekptg.model.htp.entity.HtpPermohonan;

public interface IHTPeringatan {	

	public Vector<HtpPermohonan> getSenaraiPeringatanBayaran(String search,String idUrusan,String tahunBayaran)throws Exception ;
	public Vector getSenaraiPeringatanBayaranPer(String search,String idUrusan,String tahunBayaran)throws Exception ;
	public Vector getPeringatanJenisBayaran(String search,String idUrusan,String tahunBayaran, String jenisBayaran)
		throws Exception ;
	public Vector<HtpPermohonan> getPeringatanJenisBayaranPer(String search,String idUrusan,String tahunBayaran, String jenisBayaran)
	throws Exception ;
	public Vector getSenaraiPeringatan15(String search,String idUrusan,String tbl
			, String hantar,String terima)throws Exception ;
//	public Vector<HtpPermohonan> getSenaraiPeringatanHari(String search,String idUrusan,String tbl
//			, String hantar,String terima)throws Exception ;

}
