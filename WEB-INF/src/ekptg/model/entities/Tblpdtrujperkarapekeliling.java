package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpdtrujperkarapekeliling entity. @author MyEclipse Persistence Tools
 */
public class Tblpdtrujperkarapekeliling extends
		AbstractTblpdtrujperkarapekeliling implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtrujperkarapekeliling() {
	}

	/** full constructor */
	public Tblpdtrujperkarapekeliling(String kodPerkaraPekeliling,
			String perkaraPekeliling, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(kodPerkaraPekeliling, perkaraPekeliling, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
