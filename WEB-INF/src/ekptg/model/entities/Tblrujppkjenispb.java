package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblrujppkjenispb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujppkjenispb extends AbstractTblrujppkjenispb implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujppkjenispb() {
	}

	/** minimal constructor */
	public Tblrujppkjenispb(Long idJenispb) {
		super(idJenispb);
	}

	/** full constructor */
	public Tblrujppkjenispb(Long idJenispb, String kod, String keterangan,
			String jenisDaftarPb, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkobs,
			Set tblppkborangphtas, Set tblppkborangaobs, Set tblppkborangpobs) {
		super(idJenispb, kod, keterangan, jenisDaftarPb, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblppkobs, tblppkborangphtas,
				tblppkborangaobs, tblppkborangpobs);
	}

}
