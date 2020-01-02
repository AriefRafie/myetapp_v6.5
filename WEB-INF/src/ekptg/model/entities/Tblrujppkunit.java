package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblrujppkunit entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujppkunit extends AbstractTblrujppkunit implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujppkunit() {
	}

	/** minimal constructor */
	public Tblrujppkunit(Long idUnitpsk, Long idNegeri) {
		super(idUnitpsk, idNegeri);
	}

	/** full constructor */
	public Tblrujppkunit(Long idUnitpsk, Long idNegeri, Long idJkptg,
			String namaPejabat, String keteranganUnitPsk, String namaPegawai,
			String jawatan, String statusPeg, String alamat1, String alamat2,
			String alamat3, String alamat4, String poskod, String noTel,
			String noTelSambungan, String catatan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppkbkes, Set tblppkperintahs, Set tblppkpermohonans,
			Set tblppkrayuans) {
		super(idUnitpsk, idNegeri, idJkptg, namaPejabat, keteranganUnitPsk,
				namaPegawai, jawatan, statusPeg, alamat1, alamat2, alamat3,
				alamat4, poskod, noTel, noTelSambungan, catatan, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, tblppkbkes,
				tblppkperintahs, tblppkpermohonans, tblppkrayuans);
	}

}
