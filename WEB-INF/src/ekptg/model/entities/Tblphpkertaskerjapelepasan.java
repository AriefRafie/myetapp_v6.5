package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpkertaskerjapelepasan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpkertaskerjapelepasan extends
		AbstractTblphpkertaskerjapelepasan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpkertaskerjapelepasan() {
	}

	/** minimal constructor */
	public Tblphpkertaskerjapelepasan(Long idKertaskerjapelepasan) {
		super(idKertaskerjapelepasan);
	}

	/** full constructor */
	public Tblphpkertaskerjapelepasan(Long idKertaskerjapelepasan,
			Tblphppermohonanpelepasan tblphppermohonanpelepasan,
			Long idMesyuarat, Long idKertaskerja, Long noVersi, String tujuan,
			String perihalKemajuantanah, String pihakPohonSerahbalik,
			String laporanPenilaiTanah, String ulasanKementerianTani,
			String perakuanPtpAtasmemo, String perakuanPtpAtaskertas,
			String syor, String ulasanJabatan, String ulasanMenteriKewangan,
			String perakuanKsu, String noKertas, String bilMesyuarat,
			Date tarikhMesyuarat, Date tarikhKeputusan, String keputusan,
			String ulasanKeputusan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDokumen,
			Set tblphpperenggankkplpsns) {
		super(idKertaskerjapelepasan, tblphppermohonanpelepasan, idMesyuarat,
				idKertaskerja, noVersi, tujuan, perihalKemajuantanah,
				pihakPohonSerahbalik, laporanPenilaiTanah,
				ulasanKementerianTani, perakuanPtpAtasmemo,
				perakuanPtpAtaskertas, syor, ulasanJabatan,
				ulasanMenteriKewangan, perakuanKsu, noKertas, bilMesyuarat,
				tarikhMesyuarat, tarikhKeputusan, keputusan, ulasanKeputusan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDokumen,
				tblphpperenggankkplpsns);
	}

}
