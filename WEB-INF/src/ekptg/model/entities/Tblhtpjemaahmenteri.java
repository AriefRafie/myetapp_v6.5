package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpjemaahmenteri entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpjemaahmenteri extends AbstractTblhtpjemaahmenteri implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpjemaahmenteri() {
	}

	/** minimal constructor */
	public Tblhtpjemaahmenteri(Long idJemaahmenteri,
			Tblhtppermohonan tblhtppermohonan) {
		super(idJemaahmenteri, tblhtppermohonan);
	}

	/** full constructor */
	public Tblhtpjemaahmenteri(Long idJemaahmenteri,
			Tblhtppermohonan tblhtppermohonan, Date tarikhHantarKsu,
			Date tarikhTerimaKsu, Date tarikhTerima, Date tarikhHantarPemohon,
			Date tarikhMemorandum, Date tarikhKeputusan, String noRujukanKptsn,
			String ulasan, String statusKeputusan, String tindakanLanjut,
			Date tarikhHantarDasar, String noMemorandum,
			Date tarikhMsyrtJemaah, Date tarikhKuasaPbdn, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idJemaahmenteri, tblhtppermohonan, tarikhHantarKsu,
				tarikhTerimaKsu, tarikhTerima, tarikhHantarPemohon,
				tarikhMemorandum, tarikhKeputusan, noRujukanKptsn, ulasan,
				statusKeputusan, tindakanLanjut, tarikhHantarDasar,
				noMemorandum, tarikhMsyrtJemaah, tarikhKuasaPbdn, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
