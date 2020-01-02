package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblrujppktarafkptg entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujppktarafkptg extends AbstractTblrujppktarafkptg implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujppktarafkptg() {
	}

	/** minimal constructor */
	public Tblrujppktarafkptg(Long idTarafkptg) {
		super(idTarafkptg);
	}

	/** full constructor */
	public Tblrujppktarafkptg(Long idTarafkptg, String kod, String keterangan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkborangaobs, Set tblppkborangpobs,
			Set tblppkborangapemohons, Set tblppkborangppemohons,
			Set tblppkobs, Set tblppkpermohonans, Set tblppkpemohons,
			Set tblppkhbgnfaraids) {
		super(idTarafkptg, kod, keterangan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblppkborangaobs, tblppkborangpobs,
				tblppkborangapemohons, tblppkborangppemohons, tblppkobs,
				tblppkpermohonans, tblppkpemohons, tblppkhbgnfaraids);
	}

}
