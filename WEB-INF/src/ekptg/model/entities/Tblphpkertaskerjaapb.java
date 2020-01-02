package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpkertaskerjaapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpkertaskerjaapb extends AbstractTblphpkertaskerjaapb
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpkertaskerjaapb() {
	}

	/** minimal constructor */
	public Tblphpkertaskerjaapb(Long idKertaskerjaapb) {
		super(idKertaskerjaapb);
	}

	/** full constructor */
	public Tblphpkertaskerjaapb(Long idKertaskerjaapb,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama,
			Long idMesyuarat, Long idKertaskerja, Long noVersi, String nota,
			String tajukKertas, String tujuan, String pengalaman, String syor,
			String ulasanJabatan, String ulasanJawatankuasaKhas,
			String perakuanPtpAtaskertas, String perakuanPtpAtasmemo,
			Date tarikhHantarKrtsrngksn, String flagPerakuanKsu,
			String perakuanKsu, Date tarikhPerakuanKsu, String noKertas,
			String bilMesyuarat, Date tarikhMesyuarat, Date tarikhKeputusan,
			String keputusan, String ulasanKeputusan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idDokumen) {
		super(idKertaskerjaapb, tblphppmohonnjdualpertama, idMesyuarat,
				idKertaskerja, noVersi, nota, tajukKertas, tujuan, pengalaman,
				syor, ulasanJabatan, ulasanJawatankuasaKhas,
				perakuanPtpAtaskertas, perakuanPtpAtasmemo,
				tarikhHantarKrtsrngksn, flagPerakuanKsu, perakuanKsu,
				tarikhPerakuanKsu, noKertas, bilMesyuarat, tarikhMesyuarat,
				tarikhKeputusan, keputusan, ulasanKeputusan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idDokumen);
	}

}
