package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtppemohon entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtppemohon extends AbstractTblhtppemohon implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtppemohon() {
	}

	/** minimal constructor */
	public Tblhtppemohon(Long idPemohon, Tblhtppermohonan tblhtppermohonan,
			Long idDaerah, Long idNegeri) {
		super(idPemohon, tblhtppermohonan, idDaerah, idNegeri);
	}

	/** full constructor */
	public Tblhtppemohon(Long idPemohon, Tblhtppermohonan tblhtppermohonan,
			String noPemohon, String namaPemohon, String alamatPemohon1,
			String alamatPemohon2, String alamatPemohon3, String poskod,
			Long idDaerah, Long idNegeri, String noTel, String noFax,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idPemohon, tblhtppermohonan, noPemohon, namaPemohon,
				alamatPemohon1, alamatPemohon2, alamatPemohon3, poskod,
				idDaerah, idNegeri, noTel, noFax, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
