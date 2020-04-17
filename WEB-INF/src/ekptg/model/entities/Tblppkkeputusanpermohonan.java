package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkkeputusanpermohonan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkkeputusanpermohonan extends
		AbstractTblppkkeputusanpermohonan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkkeputusanpermohonan() {
	}

	/** minimal constructor */
	public Tblppkkeputusanpermohonan(Long idKeputusanpermohonan) {
		super(idKeputusanpermohonan);
	}

	/** full constructor */
	public Tblppkkeputusanpermohonan(Long idKeputusanpermohonan,
			Long idPermohonan, String catatan,
			Date tarikhHantarBorangb, Date tarikhTerimaBorangc,
			String keputusanPermohonan, Date tarikhHantarNilaian,
			Date tarikhTerimaNilaian, Long idNegerimahkamah,
			Long idDaerahMahkamah, Long idMasuk, Date tarikhMasuk,
			Date idKemaskini, Set tblppkperbicaraans) {
		super(idKeputusanpermohonan, idPermohonan, catatan,
				tarikhHantarBorangb, tarikhTerimaBorangc, keputusanPermohonan,
				tarikhHantarNilaian, tarikhTerimaNilaian, idNegerimahkamah,
				idDaerahMahkamah, idMasuk, tarikhMasuk, idKemaskini,
				tblppkperbicaraans);
	}

}
