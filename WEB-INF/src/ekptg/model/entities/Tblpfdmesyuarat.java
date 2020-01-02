package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpfdmesyuarat entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpfdmesyuarat extends AbstractTblpfdmesyuarat implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpfdmesyuarat() {
	}

	/** minimal constructor */
	public Tblpfdmesyuarat(Long idMesyuarat) {
		super(idMesyuarat);
	}

	/** full constructor */
	public Tblpfdmesyuarat(Long idMesyuarat, String bilMesyuarat,
			String tajukMesyuarat, String kategoriMesyuarat,
			Date tarikhMesyuarat, String masaMesyuaratDari, Long idLokasi,
			Long idFail, Long idSeksyen, Long idPegawai, String catatan,
			String waktuMesyuaratDari, String masaMesyuaratHingga,
			String waktuMesyuaratHingga, Long idStatus, String idMasuk,
			Date tarikhMasuk, String idKemaskini, Date tarikhKemaskini,
			Long idDb) {
		super(idMesyuarat, bilMesyuarat, tajukMesyuarat, kategoriMesyuarat,
				tarikhMesyuarat, masaMesyuaratDari, idLokasi, idFail,
				idSeksyen, idPegawai, catatan, waktuMesyuaratDari,
				masaMesyuaratHingga, waktuMesyuaratHingga, idStatus, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}
