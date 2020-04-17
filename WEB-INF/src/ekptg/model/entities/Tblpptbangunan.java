package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptbangunan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptbangunan extends AbstractTblpptbangunan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptbangunan() {
	}

	/** full constructor */
	public Tblpptbangunan(String noBangunan, Long jenisBangunan,
			String saizBangunan, Double nilaiBangunan, String noUnitBangunan,
			String pemilik, String alamat1, String alamat2, String alamat3,
			String poskod, Long idNegeri, String noKpLama, Long idJenispb,
			Long idJenisnopb, String noPemilik, String noTel, String ulasan,
			String perihalKepentinganLain2, Long idPihakberkepentingan,
			Double kosPindah, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(noBangunan, jenisBangunan, saizBangunan, nilaiBangunan,
				noUnitBangunan, pemilik, alamat1, alamat2, alamat3, poskod,
				idNegeri, noKpLama, idJenispb, idJenisnopb, noPemilik, noTel,
				ulasan, perihalKepentinganLain2, idPihakberkepentingan,
				kosPindah, idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				idDb);
	}

}
