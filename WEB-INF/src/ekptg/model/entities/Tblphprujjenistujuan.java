package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphprujjenistujuan entity. @author MyEclipse Persistence Tools
 */
public class Tblphprujjenistujuan extends AbstractTblphprujjenistujuan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphprujjenistujuan() {
	}

	/** full constructor */
	public Tblphprujjenistujuan(String kodJenistujuan, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(kodJenistujuan, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
