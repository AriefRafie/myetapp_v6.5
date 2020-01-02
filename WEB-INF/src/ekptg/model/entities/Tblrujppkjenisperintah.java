package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblrujppkjenisperintah entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujppkjenisperintah extends AbstractTblrujppkjenisperintah
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujppkjenisperintah() {
	}

	/** minimal constructor */
	public Tblrujppkjenisperintah(Long idJenisperintah) {
		super(idJenisperintah);
	}

	/** full constructor */
	public Tblrujppkjenisperintah(Long idJenisperintah, String kod,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkrayuans,
			Set tblppkbkes, Set tblppkperintahs) {
		super(idJenisperintah, kod, keterangan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblppkrayuans, tblppkbkes,
				tblppkperintahs);
	}

}
