package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpmohontanahplpsn entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpmohontanahplpsn extends AbstractTblphpmohontanahplpsn
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpmohontanahplpsn() {
	}

	/** minimal constructor */
	public Tblphpmohontanahplpsn(Long idMohontanahplpsn) {
		super(idMohontanahplpsn);
	}

	/** full constructor */
	public Tblphpmohontanahplpsn(Long idMohontanahplpsn,
			Long idPermohonanpelepasan, Long idHakmilik, Long idNegeri,
			Long idDaerah, Long idMukim, Long idJenishm, Long idSyaratnyata,
			Long idSekatan, String noHm, Long idLot, String noLot, Double luas,
			Long idUnitluas, Long idKategori, String noWarta, Date tarikhWarta,
			Long idMenteri, Long idAgensi, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, String flagDaftarHtp) {
		super(idMohontanahplpsn, idPermohonanpelepasan, idHakmilik, idNegeri,
				idDaerah, idMukim, idJenishm, idSyaratnyata, idSekatan, noHm,
				idLot, noLot, luas, idUnitluas, idKategori, noWarta,
				tarikhWarta, idMenteri, idAgensi, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, flagDaftarHtp);
	}

}
