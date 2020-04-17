package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpindahmilik entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpindahmilik extends AbstractTblhtpindahmilik implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpindahmilik() {
	}

	/** minimal constructor */
	public Tblhtpindahmilik(Long idPindahmilik, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilikpegangan, Long idMasuk) {
		super(idPindahmilik, idPenswastaan, idHtphakmilik, idHakmilikpegangan,
				idMasuk);
	}

	/** full constructor */
	public Tblhtpindahmilik(Long idPindahmilik, Long idPenswastaan,
			Long idHtphakmilik, Long idHakmilikpegangan,
			Date tarikhTandatangan, Date tarkhHantar, Date tarikhDaftar,
			String tarikhBatalrekod, Long idMasuk, Date tarikhMasuk) {
		super(idPindahmilik, idPenswastaan, idHtphakmilik, idHakmilikpegangan,
				tarikhTandatangan, tarkhHantar, tarikhDaftar, tarikhBatalrekod,
				idMasuk, tarikhMasuk);
	}

}
