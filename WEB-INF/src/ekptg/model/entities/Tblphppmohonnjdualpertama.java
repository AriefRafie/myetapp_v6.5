package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphppmohonnjdualpertama entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppmohonnjdualpertama extends
		AbstractTblphppmohonnjdualpertama implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppmohonnjdualpertama() {
	}

	/** minimal constructor */
	public Tblphppmohonnjdualpertama(Long idPmohonnjdualpertama) {
		super(idPmohonnjdualpertama);
	}

	/** full constructor */
	public Tblphppmohonnjdualpertama(Long idPmohonnjdualpertama, String idFail,
			Long idJenistujuan, String namaProjek, String flagJenisPerjanjian,
			String antara, String danSiapa, String daripada,
			Double modalSemasa, Double modalSedia, String pengalaman,
			String lokasiPermohonan, Long tempohDipohon, Long idTempoh,
			Long bilTitik, Long idUnitisipadu, Double isipadu, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idUrusan, Long idStatus, Set tblphpkertaskerjaapbs,
			Set tblphpversikoordinats, Set tblphpprojeklesenapbs,
			Set tblphppemohonlesenapbs, Set tblphpjadualkedualesenapbs,
			Set tblphpfailapbbertindihs, Set tblphpbyrnsyrtkllsnlesenapbs,
			Set tblphpulasanteknikalapbs, Set tblphppembelipasirs) {
		super(idPmohonnjdualpertama, idFail, idJenistujuan, namaProjek,
				flagJenisPerjanjian, antara, danSiapa, daripada, modalSemasa,
				modalSedia, pengalaman, lokasiPermohonan, tempohDipohon,
				idTempoh, bilTitik, idUnitisipadu, isipadu, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idUrusan, idStatus,
				tblphpkertaskerjaapbs, tblphpversikoordinats,
				tblphpprojeklesenapbs, tblphppemohonlesenapbs,
				tblphpjadualkedualesenapbs, tblphpfailapbbertindihs,
				tblphpbyrnsyrtkllsnlesenapbs, tblphpulasanteknikalapbs,
				tblphppembelipasirs);
	}

}
