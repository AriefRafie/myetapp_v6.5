package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkboranga entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkboranga extends AbstractTblppkboranga implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkboranga() {
	}

	/** minimal constructor */
	public Tblppkboranga(Long idBoranga, Tblppkpermohonan tblppkpermohonan) {
		super(idBoranga, tblppkpermohonan);
	}

	/** full constructor */
	public Tblppkboranga(Long idBoranga, Tblppkpermohonan tblppkpermohonan,
			Date tarikhMohon, Double jumlahHtaTarikhmohon,
			Double jumlahHtaTarikhmati, Double jumlahHaTarikhmohon,
			Double jumlahHaTarikhmati, Double jumlahHartaTarikhmohon,
			Double jumlahHartaTarikhmati, String catatan, Long idNegerimhn,
			Long idDaerahmhn, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkborangapemohons,
			Set tblppkborangasimatis) {
		super(idBoranga, tblppkpermohonan, tarikhMohon, jumlahHtaTarikhmohon,
				jumlahHtaTarikhmati, jumlahHaTarikhmohon, jumlahHaTarikhmati,
				jumlahHartaTarikhmohon, jumlahHartaTarikhmati, catatan,
				idNegerimhn, idDaerahmhn, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, tblppkborangapemohons, tblppkborangasimatis);
	}

}
