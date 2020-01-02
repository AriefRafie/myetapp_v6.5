package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkborangp entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkborangp extends AbstractTblppkborangp implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkborangp() {
	}

	/** minimal constructor */
	public Tblppkborangp(Long idBorangp, Tblppkpermohonan tblppkpermohonan) {
		super(idBorangp, tblppkpermohonan);
	}

	/** full constructor */
	public Tblppkborangp(Long idBorangp, Tblppkpermohonan tblppkpermohonan,
			Date tarikhMohon, String noFailTerdahulu,
			Double jumlahHtaTarikhmohon, Double jumlahHtaTarikhmati,
			Double jumlahHaTarikhmohon, Double jumlahHaTarikhmati,
			Double jumlahHartaTarikhmohon, Double jumlahHartaTarikhmati,
			String catatan, Long idNegerimhn, Long idDaerahmhn,
			String batalKuasaPentadbir, String lantikPentadbirBaru,
			String batalPAmanah, String lantikPAmanah, String hartaTinggal,
			Double nilaiTerdahulu, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkborangpsimatis,
			Set tblppkborangppemohons) {
		super(idBorangp, tblppkpermohonan, tarikhMohon, noFailTerdahulu,
				jumlahHtaTarikhmohon, jumlahHtaTarikhmati, jumlahHaTarikhmohon,
				jumlahHaTarikhmati, jumlahHartaTarikhmohon,
				jumlahHartaTarikhmati, catatan, idNegerimhn, idDaerahmhn,
				batalKuasaPentadbir, lantikPentadbirBaru, batalPAmanah,
				lantikPAmanah, hartaTinggal, nilaiTerdahulu, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblppkborangpsimatis, tblppkborangppemohons);
	}

}
