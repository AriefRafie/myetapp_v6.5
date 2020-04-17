package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblpdtaktapinda entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtaktapinda extends AbstractTblpdtaktapinda implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtaktapinda() {
	}

	/** minimal constructor */
	public Tblpdtaktapinda(Long idAktapinda, Long noAktaAsal,
			Long noAktaPindaan, Long noAktaBaru, String namaAktaBaru,
			Long idFail) {
		super(idAktapinda, noAktaAsal, noAktaPindaan, noAktaBaru, namaAktaBaru,
				idFail);
	}

	/** full constructor */
	public Tblpdtaktapinda(Long idAktapinda, Long noAktaAsal,
			Long noAktaPindaan, Long noAktaBaru, String namaAktaBaru,
			String justifikasiPindaan, Long noMemorandumMenteri,
			String keteranganJemaahMenteri, Date tarikhKuatkuasa,
			Date tarikhDaftarPindaan, Date tarikhKuatkuasaPindaan,
			Date tarikhWarta, Date tarikhPerkenaanDiraja,
			Date tarikhPenyiaranDalamwarta, Long bil, String dirFail,
			Long idFail, String catatan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtaktapindapenggals, Set tblpdtpindaanaktas,
			Set tblpdtjadualpindas, Set tblpdtaktapindabahagians) {
		super(idAktapinda, noAktaAsal, noAktaPindaan, noAktaBaru, namaAktaBaru,
				justifikasiPindaan, noMemorandumMenteri,
				keteranganJemaahMenteri, tarikhKuatkuasa, tarikhDaftarPindaan,
				tarikhKuatkuasaPindaan, tarikhWarta, tarikhPerkenaanDiraja,
				tarikhPenyiaranDalamwarta, bil, dirFail, idFail, catatan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblpdtaktapindapenggals, tblpdtpindaanaktas,
				tblpdtjadualpindas, tblpdtaktapindabahagians);
	}

}
