package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblrujppkbuktimati entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujppkbuktimati extends AbstractTblrujppkbuktimati implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujppkbuktimati() {
	}

	/** minimal constructor */
	public Tblrujppkbuktimati(Long idBuktimati) {
		super(idBuktimati);
	}

	/** full constructor */
	public Tblrujppkbuktimati(Long idBuktimati, String kod, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkpermohonans,
			Set tblppkborangpsimatis, Set tblppkborangasimatis,
			Set tblppksimatis) {
		super(idBuktimati, kod, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblppkpermohonans, tblppkborangpsimatis,
				tblppkborangasimatis, tblppksimatis);
	}

}
