package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppttambahaward entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppttambahaward extends AbstractTblppttambahaward implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppttambahaward() {
	}

	/** full constructor */
	public Tblppttambahaward(Long idAward, Double nilaiAward,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(idAward, nilaiAward, keterangan, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, idDb);
	}

}
