package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptnotisawam entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptnotisawam extends AbstractTblpptnotisawam implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptnotisawam() {
	}

	/** full constructor */
	public Tblpptnotisawam(String tempat, Date tarikhTampal,
			Long jenisTempatTampal, Long idJenisDokumen, String bilSurat,
			String bilNotis, Long idPermohonan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(tempat, tarikhTampal, jenisTempatTampal, idJenisDokumen,
				bilSurat, bilNotis, idPermohonan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, idDb);
	}

}
