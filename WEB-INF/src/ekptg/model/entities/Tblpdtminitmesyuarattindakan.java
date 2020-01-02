package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpdtminitmesyuarattindakan entity. @author MyEclipse Persistence Tools
 */
public class Tblpdtminitmesyuarattindakan extends
		AbstractTblpdtminitmesyuarattindakan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtminitmesyuarattindakan() {
	}

	/** full constructor */
	public Tblpdtminitmesyuarattindakan(
			Long idMinitmesyuaratpara,
			String pihakBertindak, String idMasuk, Date tarikhMasuk,
			String idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(idMinitmesyuaratpara, pihakBertindak, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini,idDb);
	}

}
