package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkpermohonan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkpermohonan extends AbstractTblppkpermohonan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkpermohonan() {
	}

	/** minimal constructor */
	public Tblppkpermohonan(Long idPermohonan, Long idFail) {
		super(idPermohonan, idFail);
	}

	/** full constructor */
	public Tblppkpermohonan(Long idPermohonan,
			Tblrujppkbuktimati tblrujppkbuktimati, Tblrujppkunit tblrujppkunit,
			Tblrujppktarafkptg tblrujppktarafkptg, Long idFail,
			Date tarikhMohon, Long bilBicara, Double jumlahHtaTarikhmohon,
			Double jumlahHtaTarikhmati, Double jumlahHaTarikhmohon,
			Double jumlahHaTarikhmati, Double jumlahHartaTarikhmohon,
			Double jumlahHartaTarikhmati, String bidangKuasa,
			String flagJenisBorangc, String keputusan, String catatan,
			Long idNegerimhn, Long idDaerahmhn, Long idStatus, String seksyen,
			Long idNegeriawal, Long idDaerahawal, Long idPejawal,
			String noFailAwal, String batalKuasaPentadbir,
			String lantikPentadbir, String batalPAmanah, String lantikPAmanah,
			String hartaTinggal, String namaPemohonAwal, String flagStatusPeguam,
			String jenisFail, Double nilaiTerdahulu, String flagjenisPermohonan,
			String noPermohonanOnline, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkborangps,
			Set tblppkborangas, Set tblppkpemohons, Set tblppkbayarans,
			Set tblppkbkes, Set tblppksimatis, Set tblppkrayuans,
			Set tblppkkeputusanpermohonans) {
		super(idPermohonan, tblrujppkbuktimati, tblrujppkunit,
				tblrujppktarafkptg, idFail, tarikhMohon, bilBicara,
				jumlahHtaTarikhmohon, jumlahHtaTarikhmati, jumlahHaTarikhmohon,
				jumlahHaTarikhmati, jumlahHartaTarikhmohon,
				jumlahHartaTarikhmati, bidangKuasa, flagJenisBorangc, keputusan,
				catatan, idNegerimhn, idDaerahmhn, idStatus, seksyen,
				idNegeriawal, idDaerahawal, idPejawal, noFailAwal,
				batalKuasaPentadbir, lantikPentadbir, batalPAmanah,
				lantikPAmanah, hartaTinggal, namaPemohonAwal, flagStatusPeguam,
				jenisFail, nilaiTerdahulu, flagjenisPermohonan, noPermohonanOnline,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblppkborangps, tblppkborangas, tblppkpemohons, tblppkbayarans,
				tblppkbkes, tblppksimatis, tblppkrayuans,
				tblppkkeputusanpermohonans);
	}

}
