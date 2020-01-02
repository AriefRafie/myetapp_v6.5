package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptwarta entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptwarta extends AbstractTblpptwarta implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptwarta() {
	}

	/** full constructor */
	public Tblpptwarta(Long prosesWarta, String noWarta, Long jenisWarta,
			Date tarikhWarta, Date tarikhTerimaWarta, Long idPermohonan,
			Long idPenarikanbalik, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(prosesWarta, noWarta, jenisWarta, tarikhWarta, tarikhTerimaWarta,
				idPermohonan, idPenarikanbalik, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, idDb);
	}

}
