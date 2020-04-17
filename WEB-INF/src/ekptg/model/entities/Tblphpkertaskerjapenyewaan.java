package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphpkertaskerjapenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpkertaskerjapenyewaan extends
		AbstractTblphpkertaskerjapenyewaan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpkertaskerjapenyewaan() {
	}

	/** minimal constructor */
	public Tblphpkertaskerjapenyewaan(Long idKertaskerjapenyewaan) {
		super(idKertaskerjapenyewaan);
	}

	/** full constructor */
	public Tblphpkertaskerjapenyewaan(Long idKertaskerjapenyewaan,
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan,
			Long idMesyuarat, Long idKertaskerja, Long noVersi,
			String tajukKertas, String tujuan, String laporanTanah,
			String laporanPenilaiTanah, String ulasanKjp,
			String ulasanUrusetia, String syorUrusetia, String noKertas,
			String bilMesyuarat, Date tarikhMesyuarat, Date tarikhKeputusan,
			String keputusan, String ulasanKeputusan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idDokumen, Set tblphpperenggankkpenyewaans) {
		super(idKertaskerjapenyewaan, tblphppermohonanpenyewaan, idMesyuarat,
				idKertaskerja, noVersi, tajukKertas, tujuan, laporanTanah,
				laporanPenilaiTanah, ulasanKjp, ulasanUrusetia, syorUrusetia,
				noKertas, bilMesyuarat, tarikhMesyuarat, tarikhKeputusan,
				keputusan, ulasanKeputusan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDokumen, tblphpperenggankkpenyewaans);
	}

}
