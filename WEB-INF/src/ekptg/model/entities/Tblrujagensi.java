package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujagensi entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujagensi extends AbstractTblrujagensi implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujagensi() {
	}

	/** minimal constructor */
	public Tblrujagensi(Long idAgensi) {
		super(idAgensi);
	}

	/** full constructor */
	public Tblrujagensi(Long idAgensi, String kodAgensi, String namaAgensi,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idNegeri, String jawatan, Long idKementerian, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idAgensi, kodAgensi, namaAgensi, alamat1, alamat2, alamat3,
				poskod, idNegeri, jawatan, idKementerian, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
