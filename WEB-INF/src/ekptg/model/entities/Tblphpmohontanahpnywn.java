package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpmohontanahpnywn entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpmohontanahpnywn extends AbstractTblphpmohontanahpnywn
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpmohontanahpnywn() {
	}

	/** minimal constructor */
	public Tblphpmohontanahpnywn(Long idMohontanahpnywn) {
		super(idMohontanahpnywn);
	}

	/** full constructor */
	public Tblphpmohontanahpnywn(Long idMohontanahpnywn,
			Long idPermohonanpenyewaan, Long idHakmilik, Long idNegeri,
			Long idDaerah, Long idMukim, Long idJenishakmilik,
			Long idSyaratnyata, Long idSekatan, String noHm, Long idLot,
			String noLot, Double luas, Long idUnitluas, Long idKategori,
			String noWarta, Date tarikhWarta, Long idMenteri, Long idAgensi,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idMohontanahpnywn, idPermohonanpenyewaan, idHakmilik, idNegeri,
				idDaerah, idMukim, idJenishakmilik, idSyaratnyata, idSekatan,
				noHm, idLot, noLot, luas, idUnitluas, idKategori, noWarta,
				tarikhWarta, idMenteri, idAgensi, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
