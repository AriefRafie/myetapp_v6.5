package ekptg.model.entities;

import java.util.Date;

/**
 * Tblppttandakawasan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppttandakawasan extends AbstractTblppttandakawasan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppttandakawasan() {
	}

	/** full constructor */
	public Tblppttandakawasan(Long caraLaksana, Date tarikhMula,
			Date tarikhAkhir, String namaPegawai, String flagTanda,
			String alamatJuruukur, String ulasan, String noRujagensi,
			Date tarikhLawat, Long idPermohonan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(caraLaksana, tarikhMula, tarikhAkhir, namaPegawai, flagTanda,
				alamatJuruukur, ulasan, noRujagensi, tarikhLawat, idPermohonan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}
