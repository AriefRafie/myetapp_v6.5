package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpbebanan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpbebanan extends AbstractTblhtpbebanan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpbebanan() {
	}

	/** minimal constructor */
	public Tblhtpbebanan(Long idPihakberkepentingan, String noPerserahan,
			String jilid, String folio, Long idDaerah, Long idNegeri,
			Long idRujbebanan, Long idRujpihakberkepentingan) {
		super(idPihakberkepentingan, noPerserahan, jilid, folio, idDaerah,
				idNegeri, idRujbebanan, idRujpihakberkepentingan);
	}

	/** full constructor */
	public Tblhtpbebanan(Long idPihakberkepentingan,
			String noPihakBerkepentingan, String namaPihakBerkepentingan,
			String noPerserahan, String jilid, String folio, String jawatan,
			String statusDaftar, Date tarikhDaftar, String alamat1,
			String alamat2, String alamat3, String poskod, Long idDaerah,
			Long idNegeri, String noTel, String noFax, Long idRujbebanan,
			Long idRujpihakberkepentingan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(idPihakberkepentingan, noPihakBerkepentingan,
				namaPihakBerkepentingan, noPerserahan, jilid, folio, jawatan,
				statusDaftar, tarikhDaftar, alamat1, alamat2, alamat3, poskod,
				idDaerah, idNegeri, noTel, noFax, idRujbebanan,
				idRujpihakberkepentingan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb);
	}

}
