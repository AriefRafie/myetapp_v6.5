package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphplaporanpasir entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphplaporanpasir extends AbstractTblphplaporanpasir implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphplaporanpasir() {
	}

	/** minimal constructor */
	public Tblphplaporanpasir(Long idLaporanpasir) {
		super(idLaporanpasir);
	}

	/** full constructor */
	public Tblphplaporanpasir(Long idLaporanpasir,
			Tblphpjadualkedualesenapb tblphpjadualkedualesenapb,
			Long bulanPengambilan, Long tahunPengambilan,
			Double jumlahKuantiti, Long idUnitisipadu, Double jumlahRoyalti,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphpborangk2k3s,
			Set tblphpbayaranroyaltipasirs) {
		super(idLaporanpasir, tblphpjadualkedualesenapb, bulanPengambilan,
				tahunPengambilan, jumlahKuantiti, idUnitisipadu, jumlahRoyalti,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblphpborangk2k3s, tblphpbayaranroyaltipasirs);
	}

}
