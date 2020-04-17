package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkperbicaraan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkperbicaraan extends AbstractTblppkperbicaraan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkperbicaraan() {
	}

	/** minimal constructor */
	public Tblppkperbicaraan(Long idPerbicaraan) {
		super(idPerbicaraan);
	}

	/** full constructor */
	public Tblppkperbicaraan(Long idPerbicaraan,
			Long idKeputusanpermohonan, Long idNegeri,
			Long idUnitpsk, Date tarikhNotis, Date tarikhBicara,
			Date masaBicara, String tempatBicara, Long bilBicara,
			String alamatBicara1, String alamatBicara2, String alamatBicara3,
			String bandar, String poskod, Long idNegeribicara,
			String pegPengendali, String jenisKeputusan, String catatan,
			String sebabTangguh, String sebabBatal, String keputusanMahkamah,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppknotabicaras, Set tblppkperintahs,
			Set tblppkborangjs, Set tblppknotisperbicaraans,
			Set tblppkkolateralmsts) {
		super(idPerbicaraan, idKeputusanpermohonan, idNegeri, idUnitpsk,
				tarikhNotis, tarikhBicara, masaBicara, tempatBicara, bilBicara,
				alamatBicara1, alamatBicara2, alamatBicara3, bandar, poskod,
				idNegeribicara, pegPengendali, jenisKeputusan, catatan,
				sebabTangguh, sebabBatal, keputusanMahkamah, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, tblppknotabicaras,
				tblppkperintahs, tblppkborangjs, tblppknotisperbicaraans,
				tblppkkolateralmsts);
	}

}
