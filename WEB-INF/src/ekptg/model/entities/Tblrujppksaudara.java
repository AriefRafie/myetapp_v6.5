package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblrujppksaudara entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujppksaudara extends AbstractTblrujppksaudara implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujppksaudara() {
	}

	/** minimal constructor */
	public Tblrujppksaudara(Long idSaudara) {
		super(idSaudara);
	}

	/** full constructor */
	public Tblrujppksaudara(Long idSaudara, String kod, String keterangan,
			String jantina, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkborangpobs,
			Set tblppkborangppemohons, Set tblppkpemohons,
			Set tblppkhbgnfaraids, Set tblppkborangapemohons,
			Set tblppkborangaobs, Set tblppkobs) {
		super(idSaudara, kod, keterangan, jantina, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, tblppkborangpobs,
				tblppkborangppemohons, tblppkpemohons, tblppkhbgnfaraids,
				tblppkborangapemohons, tblppkborangaobs, tblppkobs);
	}

}
