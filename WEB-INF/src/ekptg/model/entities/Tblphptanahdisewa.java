package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphptanahdisewa entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphptanahdisewa extends AbstractTblphptanahdisewa implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphptanahdisewa() {
	}

	/** minimal constructor */
	public Tblphptanahdisewa(Long idTanahdisewa) {
		super(idTanahdisewa);
	}

	/** full constructor */
	public Tblphptanahdisewa(Long idTanahdisewa,
			Tblphpperjanjianpenyewaan tblphpperjanjianpenyewaan,
			Long idHakmilik, Long idNegeri, Long idDaerah, Long idMukim,
			Long idJenishm, Long idSyaratnyata, Long idSekatan, String noHm,
			Long idLot, String noLot, Double luas, Long idUnitluas,
			Long idKategori, String noWarta, Date tarikhWarta, Long idMenteri,
			Long idAgensi, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idTanahdisewa, tblphpperjanjianpenyewaan, idHakmilik, idNegeri,
				idDaerah, idMukim, idJenishm, idSyaratnyata, idSekatan, noHm,
				idLot, noLot, luas, idUnitluas, idKategori, noWarta,
				tarikhWarta, idMenteri, idAgensi, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
