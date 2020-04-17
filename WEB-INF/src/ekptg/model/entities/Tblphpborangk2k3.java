package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpborangk2k3 entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpborangk2k3 extends AbstractTblphpborangk2k3 implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpborangk2k3() {
	}

	/** minimal constructor */
	public Tblphpborangk2k3(Long idBorangk2k3) {
		super(idBorangk2k3);
	}

	/** full constructor */
	public Tblphpborangk2k3(Long idBorangk2k3,
			Tblphplaporanpasir tblphplaporanpasir, Long idBarge,
			Date tarikhHantar, String lokasiDibekalkan, String akuanKastam,
			String tujuan, Double kuantiti, Long idUnitisipadu,
			Double anggaranRoyalti, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idBorangk2k3, tblphplaporanpasir, idBarge, tarikhHantar,
				lokasiDibekalkan, akuanKastam, tujuan, kuantiti, idUnitisipadu,
				anggaranRoyalti, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
