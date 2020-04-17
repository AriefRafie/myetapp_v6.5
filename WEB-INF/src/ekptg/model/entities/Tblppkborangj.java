package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkborangj entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangj extends AbstractTblppkborangj implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangj() {
	}

	/** minimal constructor */
	public Tblppkborangj(Long idBorangj, Tblppkperbicaraan tblppkperbicaraan,
			String jenisRujukan) {
		super(idBorangj, tblppkperbicaraan, jenisRujukan);
	}

	/** full constructor */
	public Tblppkborangj(Long idBorangj, Tblppkperbicaraan tblppkperbicaraan,
			String jenisRujukan, String sebabTangguh, Date tarikhBicara,
			Date tarikhMohon, Long idNegerimahkamah, Long idDaerahMahkamah,
			Long idMahkamah, String catatan1, String catatan2, String catatan3,
			String catatan4, String catatan5, Date tarikhHantarBorangj,
			Date tarikhTerimaBorangj, String keputusanMahkamah,
			String catatanLain, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkborangjdtls) {
		super(idBorangj, tblppkperbicaraan, jenisRujukan, sebabTangguh,
				tarikhBicara, tarikhMohon, idNegerimahkamah, idDaerahMahkamah,
				idMahkamah, catatan1, catatan2, catatan3, catatan4, catatan5,
				tarikhHantarBorangj, tarikhTerimaBorangj, keputusanMahkamah,
				catatanLain, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblppkborangjdtls);
	}

}
